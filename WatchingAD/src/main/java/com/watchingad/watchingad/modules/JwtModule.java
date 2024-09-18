package com.watchingad.watchingad.modules;

import java.security.SignatureException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.watchingad.watchingad.api.user.vo.UserTokenVO;
import com.watchingad.watchingad.common.CommonUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;


/**
 * JWT 모듈 클래스
 * @author JeongJiHo
 * @since 2021-10-20
 */
@Component
public final class JwtModule {

	@Value("${jwt-sign-key}")
	private String SIGN_KEY;
    private final String ISSUER = "watchingad.com";
    private final String LOGIN_USER_ENTITY_KEY = "LOEK";

    /**
     * AccessToken 토큰을 생성합니다.
     * @return
     * @author JeongJiHo
     * @since 2021-10-20
     */
    public String createAccessToken(@NonNull String userEntityString) {
    	return Jwts.builder()
    			.setIssuer(ISSUER)
    			.setIssuedAt(new Date(System.currentTimeMillis()))
    			.setExpiration(new Date(System.currentTimeMillis() + (60 * 60) * 1000))
    			//.setExpiration(new Date(System.currentTimeMillis()))
    			.signWith(SignatureAlgorithm.HS512, SIGN_KEY)
    			.claim(LOGIN_USER_ENTITY_KEY, userEntityString)
    			.compact();
    }

    /**
     * Refresh Token 생성합니다.
     * @return
     * @author JeongJiHo
     * @since 2021-10-26
     */
    public String createRefreshToken() {
    	return Jwts.builder()
    			.setIssuer(ISSUER)
    			.setIssuedAt(new Date(System.currentTimeMillis()))
    			.setExpiration(new Date(System.currentTimeMillis() + (14 * 24 * 60 * 60) * 1000))
    			//.setExpiration(new Date(System.currentTimeMillis() + (15 * 60) * 1000))
    			.signWith(SignatureAlgorithm.HS512, SIGN_KEY)
    			.compact();
    }
    
    /**
     * 로그인한 유저의 entity를 가져옵니다.
     * @param token
     * @return
     * @author JeongJiHo
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     * @since 2021-10-22
     */
    public UserTokenVO getUserTokenVO(@NonNull String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, JsonMappingException, JsonProcessingException {
    	String userAsString = (String) getClaims(token).get(LOGIN_USER_ENTITY_KEY);
    	UserTokenVO userTokenVO = CommonUtils.objectMapper.readValue(userAsString, UserTokenVO.class);
    	return userTokenVO;
    }
    
    /**
     * 토큰을 검증합니다.
     * @param token
     * @throws ExpiredJwtException
     * @throws UnsupportedJwtException
     * @throws MalformedJwtException
     * @throws SignatureException
     * @throws IllegalArgumentException
     * @author JeongJiHo
     * @since 2021-11-02
     */
    public void validationToken(@NonNull String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
    	getClaims(token);
    }
    
    /**
     * Token에서 Claims를 가져옵니다.
     * @param token
     * @return
     * @author JeongJiHo
     * @since 2021-10-22
     */
    public Claims getClaims(@NonNull String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
    	return Jwts.parser().setSigningKey(SIGN_KEY).parseClaimsJws(token).getBody();
    }
    
    
}