package com.watchingad.watchingad.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.watchingad.watchingad.api.user.vo.TokenVO;
import com.watchingad.watchingad.constant.CommonConstant;

/**
 * 웹 관려 Utils Class
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
public final class WebUtils {

	public static void setAccessTokenWithRefreshHash(TokenVO tokenVO, HttpServletResponse response) {
		
		if(!StringUtils.hasText(tokenVO.getAccessToken())) {
			throw new IllegalArgumentException("accessToken is empty");
		}
		
		if(!StringUtils.hasText(tokenVO.getRefreshHash())) {
			throw new IllegalArgumentException("refreshHash is empty");
		}
		
		Cookie refreshCookie = new Cookie(CommonConstant.REFRESH_HASH, tokenVO.getRefreshHash());

		//TODO 실서버에서는 True가 필요함
		refreshCookie.setSecure(Boolean.FALSE);
		refreshCookie.setHttpOnly(Boolean.FALSE);
		refreshCookie.setPath("/");
		refreshCookie.setMaxAge(14 * 24 * 60 * 60);
		response.addCookie(refreshCookie);
		
		setAccessToken(tokenVO, response);
		
	}
	
	public static void setAccessToken(TokenVO tokenVO, HttpServletResponse response) {
		
		if(!StringUtils.hasText(tokenVO.getAccessToken())) {
			throw new IllegalArgumentException("accessToken is empty");
		}
		
		response.setHeader(CommonConstant.AUTHORIZATION_HEADER, tokenVO.getAccessToken());
		
	}
	
}
