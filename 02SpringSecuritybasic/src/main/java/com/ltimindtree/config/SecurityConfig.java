package com.ltimindtree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFiletr(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((request) -> request.requestMatchers("/save", "/modify", "/contact", "/greet")
				.permitAll().anyRequest().authenticated()).formLogin();

		return http.build();

	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {

		UserDetails pavanUser = User.withDefaultPasswordEncoder().username("pavan").password("pavan")
				.authorities("user").build();

		UserDetails varunUser = User.withDefaultPasswordEncoder().username("varun").password("varun").roles("admin")
				.build();

		UserDetails praneshUser = User.withDefaultPasswordEncoder().username("pranesh").password("pranesh")
				.roles("Manager").build();

		return new InMemoryUserDetailsManager(pavanUser, varunUser, praneshUser);
	}
}
