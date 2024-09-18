package com.watchingad.watchingad.api.signup.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.watchingad.watchingad.api.signup.entity.SignUpEmailAuthEntity;
import com.watchingad.watchingad.api.signup.repository.SignUpEmailAuthRepository;
import com.watchingad.watchingad.api.user.service.UserService;
import com.watchingad.watchingad.common.CommonUtils;
import com.watchingad.watchingad.constant.CommonConstant;
import com.watchingad.watchingad.modules.MailPostMan;
import com.watchingad.watchingad.specification.JpaSpecification;

import lombok.RequiredArgsConstructor;

/**
 * 회원가입 인증 Service Implements
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
@RequiredArgsConstructor
@Service
public class SignUpAuthServiceImpl implements SignUpAuthService{

	private final MailPostMan mailPostMan;
	private final SignUpEmailAuthRepository signUpEmailAuthRepository;
	private final UserService userService;
	
	private final String CONST_AUTH_TITLE = "WachingAD 회원가입 인증입니다.";
	private final String CONST_AUTH_TEMPLATE = "join-auth-template";
	private final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;

	@Transactional
	@Override
	public void sendAuthMail(@NonNull String sendTo, @NonNull String sessionId) throws com.watchingad.watchingad.exception.exception.MailException {
		
		// 해당 아이디가 존재하는지 확인, 존재하면 에러를 발생합니다.
		userService.checkExistId(sendTo);
		
		Map<String, Object> paramMap = new HashMap<>();
		int authNo = CommonUtils.generateAuthNo();
		paramMap.put("authKey",authNo);
		
		try {
			mailPostMan.sendMail(CONST_AUTH_TITLE,sendTo,CONST_AUTH_TEMPLATE,paramMap);
			SignUpEmailAuthEntity signUpEmailAuthEntity = SignUpEmailAuthEntity.builder()
					.authNum(authNo)
					.email(sendTo)
					.sessionId(sessionId)
					.timeLimit(LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE))
					.build();
			
			signUpEmailAuthRepository.updateUseAuthYnByEmail(sendTo);
			signUpEmailAuthRepository.save(signUpEmailAuthEntity);
		} catch (MailException | UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			throw new com.watchingad.watchingad.exception.exception.MailException("An error occurred while sending the mail.");
		}
		
	}

	@Transactional
	@Override
	public SignUpEmailAuthEntity checkAuthNum(@NonNull Integer authNum, @NonNull String sendTo, @NonNull String sessionId) throws CloneNotSupportedException {
		Specification<SignUpEmailAuthEntity> spec = Specification.where(JpaSpecification.equalObject("authNum", authNum));
		spec = spec.and(JpaSpecification.equalObject("email", sendTo));
		spec = spec.and(JpaSpecification.equalObject("sessionId", sessionId));
		spec = spec.and(JpaSpecification.greaterThanEqualDate("timeLimit", LocalDateTime.now()));
		
		SignUpEmailAuthEntity signUpEmailAuthEntity = signUpEmailAuthRepository.findOne(spec).orElseGet(SignUpEmailAuthEntity::new);
		SignUpEmailAuthEntity resultSignUpEmailAuthEntity = (SignUpEmailAuthEntity) signUpEmailAuthEntity.clone();
		if(signUpEmailAuthEntity.getIdx() != 0) {
			signUpEmailAuthEntity.setUseAuthYn(CommonConstant.COMMON_YN_Y);
		}
		
		return resultSignUpEmailAuthEntity;
	}

	@Transactional
	@Override
	public int clearEmailAuth() throws Exception {
		return signUpEmailAuthRepository.updateUseAuthYnByLessThanTimeLimit();
	}
	
}
