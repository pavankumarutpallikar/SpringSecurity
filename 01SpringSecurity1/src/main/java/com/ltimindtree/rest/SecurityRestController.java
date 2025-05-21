package com.ltimindtree.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityRestController {

	@GetMapping("/get")
	public String getMessage() {
		return "Welcome to Spring Security concept";
	}
}
