package com.watchingad.watchingad.api.signup.service;

import org.springframework.lang.NonNull;

import com.watchingad.watchingad.api.signup.entity.SignUpEmailAuthEntity;
import com.watchingad.watchingad.exception.exception.MailException;
import com.watchingad.watchingad.exception.exception.UserAlreadyJoinException;

public interface SignUpAuthService {

	/**
	 * 이메일 인증번호를 보내고, 인증 관련 데이터를 저장합니다.
	 * @param sendTo
	 * @param sessionId
	 * @throws MailException
	 * @author JeongJiHo
	 * @since 2021-09-16
	 */
	public void sendAuthMail(@NonNull String sendTo, @NonNull String sessionId) throws MailException;
	
	/**
	 * 이메일 인증번호를 체크합니다.
	 * @param authNo
	 * @param sendTo
	 * @param sessionId
	 * @return SignUpEmailAuthEntity
	 * @author JeongJiHo
	 * @since 2021-10-06
	 */
	public SignUpEmailAuthEntity checkAuthNum(@NonNull Integer authNum, @NonNull String sendTo, @NonNull String sessionId) throws CloneNotSupportedException;
	
	/**
	 * 이메일 인증 데이터를 정리합니다.
	 * @return
	 * @author JeongJiHo
	 * @since
	 */
	public int clearEmailAuth() throws Exception;
	
}
