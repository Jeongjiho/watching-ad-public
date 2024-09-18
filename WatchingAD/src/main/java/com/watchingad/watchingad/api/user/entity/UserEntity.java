package com.watchingad.watchingad.api.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 Entity
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
@Entity
@Table(name = "WT_USER")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;
	
	@Email
	@Size(max = 100, message = "이메일은 최대 100자리입니다.")
	private String id;
	@Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$", message = "비밀번호는 영문(대소문자 구분), 숫자, 특수문자 조합, 9~12자리를 입력해주세요.")
	@NotBlank(message = "비밀번호는 필수값 입니다.")
	private String password;

	@NotBlank(message = "이름은 필수값 입니다.")
	private String name;
	
	@Size(min = 11, max = 11, message = "핸드폰 번호는 11자리입니다.")
	@NotBlank(message = "핸드폰 번호는 필수값 입니다.")
	private String phone;

	private String dUuid;
	private String dOs;
	private String dModel;
	private String dOsVersion;
	
	@Column(insertable = false)
	private LocalDateTime createDatetime;
	
	private LocalDateTime updateDatetime;
	
	@Column(insertable = false)
	private String deleteYn;
	
	@Builder
	public UserEntity(int idx, String id, String password, String name, String phone, String dUuid , String dOs , String dModel, String dOsVersion, LocalDateTime updateDatetime) {
		this.idx = idx;
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.dUuid = dUuid;
		this.dOs = dOs;
		this.dModel = dModel;
		this.dOsVersion = dOsVersion;
		this.updateDatetime = updateDatetime;
	}
	
	public boolean isEmpty() {
		return (!StringUtils.hasText(id) && idx == 0) ? Boolean.TRUE : Boolean.FALSE;
	}
	
}
