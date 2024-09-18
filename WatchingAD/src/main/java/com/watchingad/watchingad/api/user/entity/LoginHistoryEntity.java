package com.watchingad.watchingad.api.user.entity;

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
 * 로그인 히스토리 Entity
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@Entity
@Table(name = "WT_LOGIN_HISTORY")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class LoginHistoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;
	
	private int userIdx;
	private String token;
	private String dUuid;
	private String dModel;
	
	@Column(insertable = false)
	private LocalDateTime createDatetime;
	
	@Column(insertable = false)
	private String deleteYn;
	
	@Builder
	public LoginHistoryEntity(int userIdx, String token, String dUuid, String dModel) {
		this.userIdx = userIdx;
		this.token = token;
		this.dUuid = dUuid;
		this.dModel = dModel;
	}
	
}
