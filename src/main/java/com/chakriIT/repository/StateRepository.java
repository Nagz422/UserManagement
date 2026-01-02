package com.chakriIT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakriIT.entity.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Integer>{

	public List<StateEntity> findByCountryCountryId(Integer countryId);
}
