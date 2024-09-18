package com.watchingad.watchingad.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.watchingad.watchingad.api.user.entity.LoginHistoryEntity;

/**
 * 로그인 히스토리 Repository
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
public interface LoginHistoryRepository extends JpaRepository<LoginHistoryEntity, Integer>, JpaSpecificationExecutor<LoginHistoryEntity> {
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE LoginHistoryEntity SET DELETE_YN = 'Y' WHERE USER_IDX = :userIdx")
	public int updateDeleteYByUserIdx(@Param(value = "userIdx") int userIdx);
	
}