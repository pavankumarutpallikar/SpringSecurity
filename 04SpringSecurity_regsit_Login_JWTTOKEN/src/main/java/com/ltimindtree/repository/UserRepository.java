package com.ltimindtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByUsername(String username);
}
