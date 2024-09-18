package com.watchingad.watchingad.api.signup.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 이메일 인증 Entity
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@Entity
@Table(name = "WT_EMAIL_AUTH")
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class SignUpEmailAuthEntity implements Cloneable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;
	private int authNum;
	private String email;
	private String sessionId;
	private LocalDateTime timeLimit;
	
	@Column(insertable = false)
	@Setter
	private String useAuthYn;
	
	@Column(insertable = false)
	private LocalDateTime createDatetime;
	
	@Builder
	public SignUpEmailAuthEntity(int authNum, String email, String sessionId, LocalDateTime timeLimit) {
		this.authNum = authNum;
		this.email = email;
		this.sessionId = sessionId;
		this.timeLimit = timeLimit;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
