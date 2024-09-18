package com.watchingad.watchingad.interceptor;

import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.watchingad.watchingad.api.user.entity.LoginHistoryEntity;
import com.watchingad.watchingad.api.user.entity.UserEntity;
import com.watchingad.watchingad.api.user.service.UserService;
import com.watchingad.watchingad.api.user.service.UserServiceImpl;
import com.watchingad.watchingad.api.user.vo.TokenVO;
import com.watchingad.watchingad.common.CommonUtils;
import com.watchingad.watchingad.common.WebUtils;
import com.watchingad.watchingad.constant.CommonConstant;
import com.watchingad.watchingad.exception.exception.AccessTokenException;
import com.watchingad.watchingad.exception.exception.NotFoundTokenException;
import com.watchingad.watchingad.modules.JwtModule;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;

/**
 * 인증 Interceptor Class
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@Component
@RequiredArgsConstructor
public final class AuthInterceptor implements HandlerInterceptor, ApplicationContextAware {

	private final int EXPIRATION_DATE_TIME_MAX = 1800000;
	private final int EXPIRATION_DATE_TIME_MIN = 0;
	private final String CONST_D_UUID = "dUuid";
	private final String CONST_D_MODEL = "dModel";
	
	private final JwtModule jwtModule;
	
	private UserService userService;
	private StringEncryptor stringEncryptor;
	private ApplicationContext context;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, JsonProcessingException {
		
		// 늦은 초기화가 필요
		if(stringEncryptor == null) {
			stringEncryptor = context.getBean(PooledPBEStringEncryptor.class);
		}
		
		if(userService == null) {
			userService = context.getBean(UserServiceImpl.class);
		}
		
		String token = request.getHeader(CommonConstant.AUTHORIZATION_HEADER);
		if(!StringUtils.hasText(token)) {
			throw new NotFoundTokenException();
		}
		
		try {
			jwtModule.getUserTokenVO(token);
		} catch (ExpiredJwtException e) {
			
			String refreshHash = CommonUtils.getCookieValue(request, CommonConstant.REFRESH_HASH);
			String refreshTokenIdx = stringEncryptor.decrypt(refreshHash);
			
			LoginHistoryEntity loginHistoryEntity = userService.getRefreshToken(Integer.valueOf(refreshTokenIdx));
			
			String refreshToken = loginHistoryEntity.getToken();
			Claims refreshTokenClaims = jwtModule.getClaims(refreshToken);
			Date refreshExpireDate = refreshTokenClaims.getExpiration();
			Date now = new Date();

			long compareTime = refreshExpireDate.getTime() - now.getTime();
			
			if(EXPIRATION_DATE_TIME_MIN < compareTime && compareTime < EXPIRATION_DATE_TIME_MAX ) {
				String dUuid = request.getParameter(CONST_D_UUID);
				String dModel = request.getParameter(CONST_D_MODEL);
				TokenVO tokenVO = userService.updateAccessTokenWithRefreshToken(loginHistoryEntity.getUserIdx(), dUuid, dModel);
				WebUtils.setAccessTokenWithRefreshHash(tokenVO, response);
			}
			else {
				TokenVO tokenVO = userService.updateAccessToken(loginHistoryEntity.getUserIdx());
				WebUtils.setAccessToken(tokenVO, response);
			}
			
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new AccessTokenException(e.getMessage());
		}
		
		return Boolean.TRUE;
		
	}
	
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
}
