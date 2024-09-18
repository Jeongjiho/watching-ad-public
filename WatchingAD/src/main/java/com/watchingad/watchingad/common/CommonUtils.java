package com.watchingad.watchingad.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchingad.watchingad.api.user.entity.UserEntity;
import com.watchingad.watchingad.api.user.vo.UserTokenVO;

import lombok.NoArgsConstructor;

/**
 * 공통 Utils Class
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@NoArgsConstructor
public final class CommonUtils {
	
	public static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 6자리 인증 키 생성
	 * @return
	 * @author JeongJiHo
	 * @since 2021-09-14
	 */
    public static int generateAuthNo() {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());
        return generator.nextInt(1000000) % 1000000;
    }
    
    /**
     * User Entity의 중요 정보를 제거합니다.
     * @param user
     * @author JeongJiHo
     * @since 2021-10-25
     */
    public static void removeCreticalInfo(@NonNull UserEntity user) {
    	if(user == null || user.isEmpty())
    		throw new IllegalArgumentException("user entity is null or empty");
    	
    	user.setPassword(null);
    }
    
    /**
     * User Entity의 객체를 User Token VO로 변경합니다.
     * @param user
     * @return
     * @author JeongJiHo
     * @since 2021-10-25
     */
    public static UserTokenVO userEntityToUserTokenVO(@NonNull UserEntity user) {
    	if(user == null || user.isEmpty())
    		throw new IllegalArgumentException("user entity is null or empty");
    	
    	UserTokenVO userTokenVO = new UserTokenVO();
    	userTokenVO.setIdx(user.getIdx());
    	userTokenVO.setId(user.getId());
    	userTokenVO.setPhone(user.getPhone());
    	userTokenVO.setDUuid(user.getDUuid());
    	userTokenVO.setDModel(user.getDModel());
    	userTokenVO.setDOs(user.getDOs());
    	userTokenVO.setDOsVersion(user.getDOsVersion());
    	userTokenVO.setCreateDateStr(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    	
    	return userTokenVO;
    }
    
    /**
     * 쿠키에서 해당 이름의 값을 가져옵니다.
     * @param request
     * @param name
     * @return
     * @author JeongJiHo
     * @since 2021-10-26
     */
    public static String getCookieValue(@NonNull HttpServletRequest request, @NonNull String name) {
    	if(request == null || name == null)
    		throw new IllegalArgumentException( (request == null ? "request" : "name") + " is null");
    	
    	Cookie[] cookies = request.getCookies();
    	String result = "";
    	
    	for(Cookie cookie : cookies) {
    		if(name.equals(cookie.getName())) {
    			result = cookie.getValue();
    			break;
    		}
    	}
    	
    	return result;
    }

}
