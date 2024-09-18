package com.watchingad.watchingad.api.user.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Token VO
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class TokenVO {

	private String accessToken;
	private String refreshToken;
	private String refreshHash;

	@Builder
	public TokenVO(String accessToken, String refreshToken, String refreshHash) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.refreshHash = refreshHash;
	}
	
}
