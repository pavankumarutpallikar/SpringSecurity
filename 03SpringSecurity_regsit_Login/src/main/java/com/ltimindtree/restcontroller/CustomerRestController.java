package com.ltimindtree.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.entity.Customer;
import com.ltimindtree.repository.CustomerRepository;

@RestController
//@RequestMapping("/api/v1/customer")
public class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/save")
	public String RegisterUser(@RequestBody Customer customer) {

		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		customerRepository.save(customer);

		return "User Registration is done";

	}
}
