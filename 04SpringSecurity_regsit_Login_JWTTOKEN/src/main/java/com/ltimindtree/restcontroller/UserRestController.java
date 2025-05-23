package com.ltimindtree.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.binding.AuthenticateRequest;
import com.ltimindtree.entity.UserEntity;
import com.ltimindtree.service.JWTTokenService;
import com.ltimindtree.service.MyUserDetailsService;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTTokenService jwtTokenService;

	@PostMapping("/register")
	public String regiterUser(UserEntity user) {
		String encodedPassword = passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encodedPassword);
		boolean savedUser = myUserDetailsService.save(user);
		if (savedUser) {
			return "User Registration is done";
		} else {
			return "USer registration is failled";
		}
	}

	@PostMapping("/login")
	public String userAuthenticate(@RequestBody AuthenticateRequest authenticateRequest) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				authenticateRequest.getUserName(), authenticateRequest.getUserPassword());
		Authentication authenticated = authenticationManager.authenticate(token);

		if (authenticated.isAuthenticated()) {
			// generate jwt token
			return jwtTokenService.generateToken(authenticateRequest.getUserName());
		} else {
			return "Invalid credentials";
		}
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Wipro Limited";
	}
}
