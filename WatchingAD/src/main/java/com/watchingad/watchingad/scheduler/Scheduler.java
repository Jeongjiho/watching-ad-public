package com.watchingad.watchingad.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.watchingad.watchingad.api.signup.service.SignUpAuthService;

import lombok.RequiredArgsConstructor;

/**
 * 스케쥴러 Class
 * @author JeongJiHo
 * @version 1.0
 * @since 2021-12-29
 */

@RequiredArgsConstructor
@Component
public final class Scheduler {
	
	private final SignUpAuthService signUpAuthService;


	@Scheduled(fixedDelay = 1000 * 60 * 60) 
	public void clearEmailAuth() throws Exception {
		signUpAuthService.clearEmailAuth();
	}
	
}
