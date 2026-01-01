package com.chakriIT.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private String name;
	
	private String eMail;
	
	private String pwd;
	
	private String pwdUpdated;
	
	private Long phno;
	
	private Integer countryId;
	
	private Integer stateId;
	
	private Integer cityId;
}
