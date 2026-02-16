package com.chakriIT.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chakriIT.dto.CityDto;
import com.chakriIT.dto.CountryDto;
import com.chakriIT.dto.QuoteApiResponseDto;
import com.chakriIT.dto.ResetPwdDto;
import com.chakriIT.dto.StateDto;
import com.chakriIT.dto.UserDto;
import com.chakriIT.entity.CityEntity;
import com.chakriIT.entity.CountryEntity;
import com.chakriIT.entity.StateEntity;
import com.chakriIT.entity.UserEntity;
import com.chakriIT.repository.CityRepository;
import com.chakriIT.repository.CountryRepository;
import com.chakriIT.repository.StateRepository;
import com.chakriIT.repository.UserRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private CountryRepository countryRepo;
	
	private StateRepository stateRepo;
	
	private CityRepository cityRepo;
	
	private UserRepository userRepo;
	
	private EmailService emailService;
	
	private ModelMapper mapper;
	
	@Override
	public List<CountryDto> getCountries() {
		List<CountryEntity> countries = countryRepo.findAll();
		
		return countries.stream()
				.map(country->mapper.map(country, CountryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<StateDto> getStates(Integer countryId) {
		System.out.println("Country Id is :"+countryId);
		List<StateEntity> states = stateRepo.findByCountryCountryId(countryId);
		
		return states.stream()
				.map(state->mapper.map(state, StateDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<CityDto> getCities(Integer stateId) {
		List<CityEntity> cities = cityRepo.findByStateStateId(stateId);
		return cities.stream()
				.map(city -> mapper.map(city, CityDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isEmailUnique(String email) {
		
		return !userRepo.existsByEmail(email);
	}

	@Override
	public boolean register(UserDto userDto) {
		UserEntity user = mapper.map(userDto, UserEntity.class);
		
		user.setPwd(generateRandomPwd(5));
		user.setPwdUpdated("No");
		
		CountryEntity country = countryRepo.findById(userDto.getCountryId()).orElseThrow();
		StateEntity states = stateRepo.findById(userDto.getStateId()).orElseThrow();
		CityEntity cities = cityRepo.findById(userDto.getCityId()).orElseThrow();
		
		user.setCountry(country);
		user.setState(states);
		user.setCity(cities);
		
		UserEntity savedUser = userRepo.save(user);
		
		if(savedUser.getUserId()!=null) {
			String subject ="Chakri IT - Your Account Created";
			String body = "<h2> Your Temporary pwd :" +user.getPwd();
			
			return emailService.sendEmail(subject, body, userDto.getEmail());
		}
		
		
		
		return false;
	}

	@Override
	public UserDto login(String email, String pwd) {
		UserEntity userEntity = userRepo.findByEmailAndPwd(email, pwd);
		
		if(userEntity!=null) {
			
			/*UserDto dto = new UserDto();
			BeanUtils.copyProperties(userEntity, dto);
			return dto; */
			
			return mapper.map(userEntity, UserDto.class);
			
			
		}
		return null;
	}

	@Override
	public boolean resetPwd(ResetPwdDto resetPwdDto) {
		UserEntity userEntity = userRepo.findByEmail(resetPwdDto.getEmail());
		
		if(userEntity!=null) {
			
			userEntity.setPwd(resetPwdDto.getNewPwd());
			userEntity.setPwdUpdated("Yes");
			userRepo.save(userEntity);
			return true;
		}		
		
		return false;
	}

	@Override
	public QuoteApiResponseDto getQuote() {
		
		String apiUrl = "https://dummyjson.com/quotes/random";
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<QuoteApiResponseDto> forEntity = rt.getForEntity(apiUrl, QuoteApiResponseDto.class);
		return forEntity.getBody();
	}
	
	private String generateRandomPwd(int length) {
		
		Random random = new Random();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		
		StringBuffer buffer = new StringBuffer(chars);
		
		for(int i =0; i<length; i++) {
			int ramdomIndex = random.nextInt(chars.length());
			char ch = chars.charAt(ramdomIndex);
			buffer.append(ch);
		}
		return buffer.toString();
	}
}
