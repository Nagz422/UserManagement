package com.chakriIT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakriIT.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer>{
	
	public List<CityEntity> findByStateStatId(Integer stateId);
}
