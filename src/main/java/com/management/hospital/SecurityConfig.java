package com.management.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.management.hospital.controller.Mappings;
import com.management.hospital.model.User;
import com.management.hospital.repository.UserRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserRepository userRepository;
	private final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	public SecurityConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		for (User user : userRepository.findAll()) {

			if (user.getRole() == 0) {
				auth.inMemoryAuthentication().withUser(user.getName()).password(user.getPassword()).roles("USER");
				LOGGER.info("USER ROLE INSIDE>>>>>> "+user);
			} else if (user.getRole() == 1) {
				auth.inMemoryAuthentication().withUser(user.getName())
				.password(user.getPassword()).roles("USER", "ADMIN");
				LOGGER.info("ADMIN ROLE INSIDE>>>>>> "+user);
			}
		}
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// USER ROLES, CAN ONLY VIEW DOCTORS AND PATIENTS
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, Mappings.SHOW_DOCTORS).hasRole("USER")
				.antMatchers(HttpMethod.GET, Mappings.SHOW_A_DOCTOR).hasRole("USER").and().csrf().disable();

		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, Mappings.SHOW_PATIENTS).hasRole("USER")
				.antMatchers(HttpMethod.GET, Mappings.SHOW_A_PATIENT).hasRole("USER").and().csrf().disable();
		// END OF USER ROLES

		// ADMIN ROLES, CAN CRUD OVER DOCTORS AND PATIENTS
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, Mappings.ADD_A_DOCTOR).hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, Mappings.UPDATE_A_DOCTOR).hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, Mappings.DELETE_A_DOCTOR).hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, Mappings.GET_ADD_PATIENT_FORM).hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, Mappings.ADD_A_PATIENT).hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, Mappings.GET_UPDATE_PATIENT_FORM).hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, Mappings.UPDATE_A_PATIENT).hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, Mappings.DELETE_A_PATIENT).hasRole("ADMIN").and().csrf().disable();
		// END OF ADMIN ROLES
	}
}