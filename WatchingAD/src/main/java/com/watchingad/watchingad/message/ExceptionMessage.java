package com.watchingad.watchingad.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Exception Message Class
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@Getter
@Setter
public final class ExceptionMessage {
	private String message;
	private HttpStatus httpStatus;
	private int httpStatusCode;
	private String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	
	@Builder
	private ExceptionMessage(String message, HttpStatus httpStatus, int httpStatusCode) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatusCode;
	}
}
