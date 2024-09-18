package com.watchingad.watchingad.api.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watchingad.watchingad.api.signup.entity.SignUpConfigEntity;

/**
 * 회원가입 약관 Repository
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
public interface SignUpConfigRepository extends JpaRepository<SignUpConfigEntity, Long> {
	
	public SignUpConfigEntity findFirstByOrderByIdxDesc();
	
}