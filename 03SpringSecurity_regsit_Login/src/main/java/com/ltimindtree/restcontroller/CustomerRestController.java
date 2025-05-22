package com.ltimindtree.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

	@Autowired
	private AuthenticationManager auManager;

	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestBody Customer customer) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customer.getUserName(),
				customer.getPassword());
		Authentication authenticate = auManager.authenticate(token);

		if (authenticate.isAuthenticated()) {
			return new ResponseEntity<>("Welcome to Ltimindtree Limited", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/register")
	public String RegisterUser(@RequestBody Customer customer) {

		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		customerRepository.save(customer);

		return "User Registration is done";

	}
}
