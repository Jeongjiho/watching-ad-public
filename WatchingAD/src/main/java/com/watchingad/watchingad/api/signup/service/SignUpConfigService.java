package com.watchingad.watchingad.api.signup.service;

import com.watchingad.watchingad.api.signup.entity.SignUpConfigEntity;
import com.watchingad.watchingad.exception.exception.MailException;

public interface SignUpConfigService {

	/**
	 * 회원가입 동의 시 동의 내용 데이터를 가져옵니다.
	 * @return
	 * @author JeongJiHo
	 * @since 2021-10-06
	 */
	public SignUpConfigEntity getLatestConfig();
	
}
