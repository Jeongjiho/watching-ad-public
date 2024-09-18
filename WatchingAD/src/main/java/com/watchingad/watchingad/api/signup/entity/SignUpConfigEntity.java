package com.watchingad.watchingad.api.signup.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 회원가입 약관 Entity
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@Entity
@Table(name = "WT_SIGN_UP_CONFIG")
@Getter
@ToString
@EqualsAndHashCode
public final class SignUpConfigEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;
	private String agreeContent1;
	private String agreeContent2;
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;
	private char deleteYn;
}
