package com.chakriIT.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chakriIT.dto.*;
import com.chakriIT.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
//@RequestMapping("/api")
public class UserRestController {

	private UserService userService;
	
	@GetMapping("/countries")
	public ResponseEntity<ApiResponse<List<CountryDto>>> getCountries(){
		
		ApiResponse response = new ApiResponse();
		List<CountryDto> countries = (List<CountryDto>) userService.getCountries();
		if(countries.isEmpty()) {
			response.setStatus(500);
			response.setMessage("No Countries Found");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			response.setStatus(200);
			response.setMessage("Fetched Countries Successfully");
			response.setData(countries);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@GetMapping("/states/{countryId}")
	public ResponseEntity<ApiResponse<List<StateDto>>> getStates(@PathVariable Integer countryId){
		
		List<StateDto> states = userService.getStates(countryId);
		
		ApiResponse<List<StateDto>> response = new ApiResponse();
		
		if(states.isEmpty()) {
			
			response.setStatus(500);
			response.setMessage("No States Foud");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} else {
			
			response.setStatus(200);
			response.setMessage("States fetched Successfully");
			response.setData(states);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}
	}
	
	@GetMapping("/cities/{stateId}")
	public ResponseEntity<ApiResponse<List<CityDto>>> getCities(@PathVariable Integer stateId){
		
		List<CityDto> cities = userService.getCities(stateId);
		
		ApiResponse<List<CityDto>> response = new ApiResponse();
		
		if(cities.isEmpty()) {
			
			response.setStatus(500);
			response.setMessage("No Cities Foud");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		} else {
			
			response.setStatus(200);
			response.setMessage("Cities fetched Successfully");
			response.setData(cities);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}
	}
	
	//http://localhost:8080/unique/test@gmail.com
	@GetMapping("/unique/{email}")
	public ResponseEntity<ApiResponse<String>> checkEmail(@PathVariable String email){
		
		ApiResponse<String> response = new ApiResponse();
		
		boolean isEnquie = userService.isEmailUnique(email);
		
		if(isEnquie) {
			response.setStatus(200);
			response.setMessage("No Email Foud");
			response.setData("Unique");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(200);
			response.setMessage("Duplicate Email Foud");
			response.setData("Duplicate");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@PostMapping("/user")
	public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody UserDto userDto){
		ApiResponse<String> response = new ApiResponse<>();
		
		boolean isRegistered = userService.register(userDto);
		
		if(isRegistered) {
			response.setStatus(200);
			response.setMessage("Registration Sucessfully");
			response.setData("SUCCESS");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Failed to Register");
			response.setData("FAILED");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody UserDto userDto){
		
		ApiResponse<UserDto> response = new ApiResponse();
		UserDto user = userService.login(userDto.getEmail(), userDto.getPwd());
		
		if(user!=null) {
			
			response.setStatus(200);
			response.setMessage("Login Success");
			response.setData(user);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			
			response.setStatus(401);
			response.setMessage("Invalid Credentials");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/reset-pwd")
	public ResponseEntity<ApiResponse<String>> resetPwd(@RequestBody ResetPwdDto resetPwdDto){
		
		ApiResponse<String> response = new ApiResponse();
		boolean isPwdUpdated = userService.resetPwd(resetPwdDto);
		if(isPwdUpdated) {
			response.setStatus(200);
			response.setMessage("Password updated successfully");
			response.setData("SUCCESS");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("Record Not Found");
			response.setData("FAILED");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/quote")
	public ResponseEntity<ApiResponse<QuoteApiResponseDto>> getQuotation(){
		ApiResponse<QuoteApiResponseDto> response = new ApiResponse();
		
		QuoteApiResponseDto quote = userService.getQuote();
		
		if(quote!=null) {
			response.setStatus(200);
			response.setMessage("Quote fetched successfully");
			response.setData(quote);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.setStatus(500);
			response.setMessage("Quote fetching Failed");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}