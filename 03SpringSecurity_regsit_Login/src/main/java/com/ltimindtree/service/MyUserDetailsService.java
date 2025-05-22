package com.ltimindtree.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ltimindtree.entity.Customer;
import com.ltimindtree.repository.CustomerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Customer customer =	customerRepository.findByUserName(username);
	
	
		return new User(customer.getUserName(), customer.getPassword(), Collections.emptyList());
	}

}
