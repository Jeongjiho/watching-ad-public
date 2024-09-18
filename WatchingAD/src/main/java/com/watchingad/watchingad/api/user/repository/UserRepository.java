package com.watchingad.watchingad.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.watchingad.watchingad.api.signup.entity.SignUpEmailAuthEntity;
import com.watchingad.watchingad.api.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

}