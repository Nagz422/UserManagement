package com.chakriIT.service;

import java.util.List;

import com.chakriIT.dto.CityDto;
import com.chakriIT.dto.CountryDto;
import com.chakriIT.dto.QuoteApiResponseDto;
import com.chakriIT.dto.ResetPwdDto;
import com.chakriIT.dto.StateDto;
import com.chakriIT.dto.UserDto;

public interface UserService {
	
	public List<CountryDto> getCountries();
	
	public List<StateDto> getStates(Integer countryId);
	
	public List<CityDto> getCities(Integer stateId);
	
	public boolean isEmailUnique(String email);
	
	public boolean register(UserDto userDto);
	
	public UserDto login(String email, String pwd);
	
	public boolean resetPwd(ResetPwdDto resetPwdDto);
	
	public QuoteApiResponseDto getQuote();
}
