package com.watchingad.watchingad.api.signup.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watchingad.watchingad.api.signup.entity.SignUpConfigEntity;
import com.watchingad.watchingad.api.signup.service.SignUpAuthService;
import com.watchingad.watchingad.api.signup.service.SignUpConfigService;
import com.watchingad.watchingad.exception.exception.MailException;
import com.watchingad.watchingad.message.SuccessMessage;

import lombok.RequiredArgsConstructor;

/**
 * 회원가입 controller
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/sign-up")
public final class SignUpController {
	
	private final SignUpConfigService signUpConfigService;
	private final SignUpAuthService signUpAuthService;

	@GetMapping(value = "/get-latest-config")
	public SuccessMessage<Object> getLatestConfig(HttpServletRequest request) {
		SignUpConfigEntity dto = signUpConfigService.getLatestConfig();
		SuccessMessage<Object> successMessage = SuccessMessage.builder().data(dto).build();
		return successMessage;
	}
	
	@PostMapping(value = "/send-auth-mail")
	public SuccessMessage<Object> sendAuthMail(@RequestParam(value = "sendTo", required = true) @Email String sendTo, HttpSession session) throws MailException {
		signUpAuthService.sendAuthMail(sendTo, session.getId());
		SuccessMessage<Object> successMessage = SuccessMessage.builder().build();
		return successMessage;
	}
	
	@GetMapping(value = "/check-auth-num")
	public SuccessMessage<Object> checkAuthNum(@RequestParam(value = "sendTo", required = true) @Email String sendTo, @RequestParam(value = "authNum", required = true) Integer authNum, HttpSession session) throws CloneNotSupportedException {
		SuccessMessage<Object> successMessage = SuccessMessage.builder().data(signUpAuthService.checkAuthNum(authNum, sendTo, session.getId())).build();
		return successMessage;
	}
	
}
