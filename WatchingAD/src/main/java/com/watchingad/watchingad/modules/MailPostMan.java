package com.watchingad.watchingad.modules;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


/**
 * 이메일 보내는 클래스
 * @author JeongJiHo
 * @since 2021-09-14
 */
@Component
public final class MailPostMan {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SpringTemplateEngine springTemplateEngine;
	
	private final String CONST_FROM_EMAIL = "claraster84@gmail.com";
	private final String CONST_FROM_NAME = "WatchingAD";
	
	/**
	 * 메일을 보냅니다.
	 * @param title
	 * @param contents
	 * @param sendTo
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * @author JeongJiHo
	 * @since 2021-09-14
	 */
	public void sendMail(@NonNull String title, @NonNull String sendTo, @NonNull String templateName, @Nullable Map<String, Object> paramMap) throws MessagingException, UnsupportedEncodingException, MailException {
		
		if(!StringUtils.hasText(title)) {
			throw new IllegalArgumentException("title is empty");
		}
		
		if(!StringUtils.hasText(sendTo)) {
			throw new IllegalArgumentException("sendTo is empty");
		}
		
		if(!StringUtils.hasText(templateName)) {
			throw new IllegalArgumentException("templateName is empty");
		}
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setSubject(title);
		helper.setTo(sendTo);
		helper.setFrom(new InternetAddress(CONST_FROM_EMAIL, CONST_FROM_NAME));
		helper.setText(getHtml(templateName, paramMap), true);
        
		javaMailSender.send(message);
		
	}
	
	/**
	 * 메일을 보낼 HTML을 가져옵니다.
	 * @param templateName
	 * @param paramMap
	 * @return
	 * @author JeongJiHo
	 * @since 2021-09-14
	 */
	private String getHtml(@NonNull String templateName, @Nullable Map<String, Object> paramMap) {
		Context context = new Context();
		context.setVariables(paramMap);
		return springTemplateEngine.process("join-auth-template",context);
	}
	
}