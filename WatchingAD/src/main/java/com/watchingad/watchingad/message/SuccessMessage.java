package com.watchingad.watchingad.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Success Message Class
 * @author JeongJiHo
 * @param <T>
 * @version 1.0
 * @since 2021-12-29
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class SuccessMessage<T> {
	
	public static final String OK_MESSAGE = "성공적으로 수행하였습니다.";
	
	private int code = HttpStatus.OK.value();
	private HttpStatus httpStatus = HttpStatus.OK;
	private String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	private String message;
	private T data;

	@Builder
	public SuccessMessage(String message, T data) {
		if(StringUtils.hasText(message))
			this.message = message;
		else
			this.message = OK_MESSAGE;
		
		this.data = data;
	}
}
