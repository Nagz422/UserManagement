package com.chakriIT.dto;

import lombok.Data;

@Data
public class ResetPwdDto {
	
	private String eMail;
	
	private String oldPwd;
	
	private String newPwd;
	
	private String confirmPwd;
}
