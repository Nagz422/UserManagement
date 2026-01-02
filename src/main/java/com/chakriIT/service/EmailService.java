package com.chakriIT.service;

import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
	
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body, String to) {
	
		//Logic
		return true;
	}
}
