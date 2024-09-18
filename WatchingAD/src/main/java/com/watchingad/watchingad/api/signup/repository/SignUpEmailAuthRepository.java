package com.watchingad.watchingad.api.signup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.watchingad.watchingad.api.signup.entity.SignUpEmailAuthEntity;

/**
 * 이메일 인증 Repository
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
public interface SignUpEmailAuthRepository extends JpaRepository<SignUpEmailAuthEntity, Long>, JpaSpecificationExecutor<SignUpEmailAuthEntity> {
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE SignUpEmailAuthEntity SET USE_AUTH_YN = 'Y' WHERE EMAIL = :email")
    public int updateUseAuthYnByEmail(@Param(value = "email") String email);
	
	@Modifying
	@Query("UPDATE SignUpEmailAuthEntity SET USE_AUTH_YN = 'Y' WHERE TIME_LIMIT < NOW() AND USE_AUTH_YN = 'N'")
	public int updateUseAuthYnByLessThanTimeLimit();
	
}