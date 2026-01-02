package com.chakriIT.service;

import java.util.List;

<<<<<<< HEAD
import com.chakriIT.dto.CityDto;
import com.chakriIT.dto.CountryDto;
import com.chakriIT.dto.QuoteApiResponseDto;
import com.chakriIT.dto.ResetPwdDto;
import com.chakriIT.dto.StateDto;
import com.chakriIT.dto.UserDto;
=======
import com.chakriIT.dto.*;
>>>>>>> 1ee80c2 (Developed Repository, Service and added model mapper dependency in pom.xml)

public interface UserService {
	
	public List<CountryDto> getCountries();
	
	public List<StateDto> getStates(Integer countryId);
	
	public List<CityDto> getCities(Integer stateId);
	
	public boolean isEmailUnique(String email);
	
	public boolean register(UserDto userDto);
	
	public UserDto login(String email, String pwd);
	
<<<<<<< HEAD
	public boolean resetPwdd(ResetPwdDto resetPwdDto);
=======
	public boolean resetPwd(ResetPwdDto resetPwdDto);
>>>>>>> 1ee80c2 (Developed Repository, Service and added model mapper dependency in pom.xml)
	
	public QuoteApiResponseDto getQuote();
}
