package com.chakriIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakriIT.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer>{

}
