package com.watchingad.watchingad.api.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.watchingad.watchingad.api.user.entity.UserEntity;
import com.watchingad.watchingad.api.user.service.UserService;
import com.watchingad.watchingad.api.user.vo.TokenVO;
import com.watchingad.watchingad.common.WebUtils;
import com.watchingad.watchingad.message.SuccessMessage;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 Controller
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public final class UserController {
	
	private final UserService userService;
	
	@PostMapping(value = "/save")
	public SuccessMessage<Object> save(@Valid UserEntity user) {
		userService.save(user);
		return SuccessMessage.builder().build();
	}
	
	@PostMapping(value = "/login")
	public SuccessMessage<Object> login(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "dUuid", required = true) String dUuid, @RequestParam(value = "dModel", required = true) String dModel,
			HttpServletRequest requeset, HttpServletResponse response, HttpSession session) throws JsonProcessingException {
		TokenVO tokenVO = userService.login(id, password, dUuid, dModel, session.getId());
		WebUtils.setAccessTokenWithRefreshHash(tokenVO, response);
		return SuccessMessage.builder().build();
	}
	
}