package com.ltimindtree.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SecurityRestController {

	@GetMapping("/contact")
	public String contact() {
		return "Contact number : +91 9148303424";
	}

	@GetMapping("/msg")
	public String Message() {
		return "Welcome to LTIMindtree Limited";
	}

	@GetMapping("/greet")
	public String greetMsg() {
		return "Good Morning LTIMindtree";
	}
	
	@GetMapping("/save")
	public String save() {
		return "Employee Data saved";
	}

}