package com.watchingad.watchingad.exception.handler;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.watchingad.watchingad.constant.ExceptionConstant;
import com.watchingad.watchingad.exception.exception.AccessTokenException;
import com.watchingad.watchingad.exception.exception.MailException;
import com.watchingad.watchingad.exception.exception.NotFoundTokenException;
import com.watchingad.watchingad.exception.exception.NotFoundUserException;
import com.watchingad.watchingad.exception.exception.UserAlreadyJoinException;
import com.watchingad.watchingad.message.ExceptionMessage;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * API Exception Handler Class
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@ControllerAdvice
public final class ApiExceptionHandler {

	/**
	 * 필수 파라메터 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Object> illegalArgumentExceptionHandler(IllegalArgumentException e) {
		e.printStackTrace();
		return makeResponseEntity(StringUtils.hasText(e.getMessage()) ? e.getMessage() : ExceptionConstant.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	/**
	 * 메일 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = MailException.class)
	public ResponseEntity<Object> mailExceptionHandler(MailException e) {
		e.printStackTrace();
		return makeResponseEntity(ExceptionConstant.MAIL_EXCEPTION_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	/**
	 * 사용자 회원가입 시 이미 있으면 발생되는 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = UserAlreadyJoinException.class)
	public ResponseEntity<Object> userAlreadyJoinExceptionHandler(UserAlreadyJoinException e) {
		e.printStackTrace();
		return makeResponseEntity(ExceptionConstant.USER_ALREADY_EXCEPTION_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	/**
	 * 회원을 못 찾을 시 발생하는 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = NotFoundUserException.class)
	public ResponseEntity<Object> notFoundUserExceptionHandler(NotFoundUserException e) {
		e.printStackTrace();
		return makeResponseEntity(ExceptionConstant.NOT_FOUND_USER_EXCEPTION_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	/**
	 * Access Token을 못 찾을 시 발생되는 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = NotFoundTokenException.class)
	public ResponseEntity<Object> notFoundUserExceptionHandler(NotFoundTokenException e) {
		e.printStackTrace();
		return makeResponseEntity(ExceptionConstant.NOT_FOUND_TOKEN_EXCEPTION_MESSAGE, HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
	}
	
	/**
	 * 
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException e) {
		e.printStackTrace();
		StringBuilder message = new StringBuilder();
	    if(e.getConstraintViolations().size() > 1) {
	    	e.getConstraintViolations().forEach(v -> message.append(v.getMessage().concat("\n")));
	    }
	    else {
	    	e.getConstraintViolations().forEach(v -> message.append(v.getMessage()));
	    }
		return makeResponseEntity(message.toString(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	/**
	 * Parameter 유효성 검증 시 틀리면 발생하는 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = BindException.class)
	public ResponseEntity<Object> bindExceptionHandler(BindException e) {
		e.printStackTrace();
		StringBuilder message = new StringBuilder();
		if(e.getAllErrors().size() > 1) {
			e.getAllErrors().forEach(oe -> message.append(oe.getDefaultMessage().concat("\n")));
	    }
	    else {
	    	e.getAllErrors().forEach(oe -> message.append(oe.getDefaultMessage()));
	    }
		return makeResponseEntity(message.toString(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
	}
	
	/**
	 * AccessToken Decode 시 발생되는 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = AccessTokenException.class)
	public ResponseEntity<Object> accessTokenExceptionHandler(AccessTokenException e) {
		e.printStackTrace();
		return makeResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
	}
	
	/**
	 * AccessToken 만료 시 발생하는 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 * @since 2021-12-29
	 */
	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<Object> expiredJwtExceptionExceptionHandler(ExpiredJwtException e) {
		e.printStackTrace();
		return makeResponseEntity(ExceptionConstant.TOKEN_IS_EXPIRE, HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
	}
	
	/**
	 * 공통 Exception
	 * @param e
	 * @return ResponseEntity<Object>
	 * @author JeongJiHo
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exceptionHandler(Exception e) {
		e.printStackTrace();
		return makeResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	private ResponseEntity<Object> makeResponseEntity(String message, HttpStatus httpStatus, int httpStatusCode) {
		ExceptionMessage exceptionMessage = ExceptionMessage.builder()
				.message(message)
				.httpStatus(httpStatus)
				.httpStatusCode(httpStatus.value())
				.build();
		return new ResponseEntity<>(exceptionMessage, httpStatus);
	}
	
}
