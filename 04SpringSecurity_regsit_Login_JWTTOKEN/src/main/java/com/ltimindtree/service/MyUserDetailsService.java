package com.ltimindtree.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ltimindtree.entity.UserEntity;
import com.ltimindtree.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username);
		return new User(userEntity.getUserName(), userEntity.getUserPassword(), Collections.emptyList());
	}

	public boolean save(UserEntity user) {
		userRepository.save(user);
		return user.getUserId()!=null;

	}

}
