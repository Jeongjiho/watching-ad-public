package com.watchingad.watchingad.api.user.service;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.watchingad.watchingad.api.user.entity.LoginHistoryEntity;
import com.watchingad.watchingad.api.user.entity.UserEntity;
import com.watchingad.watchingad.api.user.vo.TokenVO;

public interface UserService {

	/**
	 * 사용자를 저장합니다.
	 * @param user
	 * @return
	 * @author JeongJiHo
	 * @since 2021-10-13
	 */
	public boolean save(@NonNull UserEntity user);
	
	/**
	 * 해당 아이디가 있는지 확인합니다.
	 * 해당 아이디가 존재할 경우 에러를 던져줍니다.
	 * @param id
	 * @return
	 * @author JeongJiHo
	 * @since 2021-10-13
	 */
	public void checkExistId(@NonNull String id);
	
	/**
	 * 로그인 처리
	 * @param id
	 * @param password
	 * @author JeongJiHo
	 * @since 2021-10-20
	 */
	public TokenVO login(@NonNull String id, @NonNull String password, @NonNull String dUuid, @NonNull String dModel, @NonNull String sessionId) throws JsonProcessingException;
	
	/**
	 * LoginHistoryIdx값으로 Refresh Token값을 가져옵니다.
	 * @param id
	 * @author JeongJiHo
	 * @since 2021-10-26
	 */
	public LoginHistoryEntity getRefreshToken(@NonNull int idx);
	
	/**
	 * 회원 IDX로 회원 정보를 가져와 Refresh Token, Access Token을 갱신합니다. 
	 * @param idx
	 * @return
	 * @author JeongJiHo
	 * @since 2021-10-28
	 */
	public TokenVO updateAccessTokenWithRefreshToken(@NonNull int idx, @NonNull String dUuid, @NonNull String dModel) throws JsonProcessingException;
	
	/**
	 * 회원 IDX로 회원 정보를 가져와 Access Token을 갱신합니다.
	 * @param idx
	 * @return
	 * @author JeongJiHo
	 * @since 2021-10-28
	 */
	public TokenVO updateAccessToken(@NonNull int idx) throws JsonProcessingException;
	
	/**
	 * 유저의 단일 객체를 가져옵니다.
	 * @param idx
	 * @return
	 * @author JeongJiHo
	 * @since 2021-10-28
	 */
	public UserEntity getUser(@NonNull int idx);
		
}
