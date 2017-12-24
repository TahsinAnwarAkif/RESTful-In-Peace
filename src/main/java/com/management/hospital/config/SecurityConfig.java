package com.management.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.management.hospital.controller.Constants;
import com.management.hospital.controller.Mappings;
import com.management.hospital.sevice.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomUserDetailsService userDetailsService;

	public SecurityConfig(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);
		// .passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		
		// USER ROLES, CAN ONLY VIEW DOCTORS AND PATIENTS
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, Mappings.SHOW_DOCTORS, Mappings.SHOW_A_DOCTOR,
				Mappings.SHOW_PATIENTS, Mappings.SHOW_A_PATIENT).hasAnyRole(Constants.USER, Constants.ADMIN).and().formLogin()
				.permitAll();
		// END OF USER ROLES

		// ADMIN ROLES, CAN CRUD OVER DOCTORS AND PATIENTS
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, Mappings.ADD_A_DOCTOR, Mappings.ADD_A_PATIENT)
				.hasRole(Constants.ADMIN).antMatchers(HttpMethod.PUT, Mappings.UPDATE_A_DOCTOR, Mappings.UPDATE_A_PATIENT)
				.hasRole(Constants.ADMIN).antMatchers(HttpMethod.DELETE, Mappings.DELETE_A_DOCTOR, Mappings.DELETE_A_PATIENT)
				.hasRole(Constants.ADMIN)
				.antMatchers(HttpMethod.GET, Mappings.GET_ADD_PATIENT_FORM, Mappings.GET_UPDATE_PATIENT_FORM)
				.hasRole(Constants.ADMIN);
		// END OF ADMIN ROLES
	}

	// private PasswordEncoder getPasswordEncoder() {
	// return new PasswordEncoder() {
	// @Override
	// public String encode(CharSequence charSequence) {
	// return charSequence.toString();
	// }
	//
	// @Override
	// public boolean matches(CharSequence charSequence, String s) {
	// return true;
	// }
	// };
	// }
}
