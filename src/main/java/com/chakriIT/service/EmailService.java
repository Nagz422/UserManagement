package com.chakriIT.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body, String to) {
	
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		try {
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			
			mailSender.send(mimeMessage);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return false;
	}
}
