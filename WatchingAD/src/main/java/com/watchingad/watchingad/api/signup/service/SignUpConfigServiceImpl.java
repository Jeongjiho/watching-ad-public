package com.watchingad.watchingad.api.signup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.watchingad.watchingad.api.signup.entity.SignUpConfigEntity;
import com.watchingad.watchingad.api.signup.repository.SignUpConfigRepository;

import lombok.RequiredArgsConstructor;

/**
 * 회원가입 약관 Service Implements
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
@RequiredArgsConstructor
@Service
public class SignUpConfigServiceImpl implements SignUpConfigService{

	private final SignUpConfigRepository signUpConfigRepository;
	
	@Override
	@Transactional(readOnly = true)
	public SignUpConfigEntity getLatestConfig() {
		return signUpConfigRepository.findFirstByOrderByIdxDesc();
	}

}
