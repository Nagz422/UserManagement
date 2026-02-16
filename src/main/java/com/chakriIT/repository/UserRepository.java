package com.chakriIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chakriIT.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	public boolean existsByEmail(String email);
	
	public UserEntity findByEmailAndPwd(String email, String pwd);
	
	public UserEntity findByEmail(String email);
}
