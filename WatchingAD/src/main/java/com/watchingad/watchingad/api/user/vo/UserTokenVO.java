package com.watchingad.watchingad.api.user.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 Token VO
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class UserTokenVO {
	
	private int idx;
	private String id;
	private String name;
	private String phone;
	private String dUuid;
	private String dOs;
	private String dModel;
	private String dOsVersion;
	private String createDateStr;

}
