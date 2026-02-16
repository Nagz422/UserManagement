package com.chakriIT.cofig;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chakriIT.dto.UserDto;
import com.chakriIT.entity.UserEntity;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper getInstance() {
		
		ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
              .setMatchingStrategy(MatchingStrategies.STRICT);
        
        mapper.typeMap(UserEntity.class, UserDto.class)
        .addMappings(m -> {
            m.map(src -> src.getCountry().getCountryId(), UserDto::setCountryId);
            m.map(src -> src.getState().getStateId(), UserDto::setStateId);
            m.map(src -> src.getCity().getCityId(), UserDto::setCityId);
        });
        
        return mapper;
		//return new ModelMapper();
	}
	
}
