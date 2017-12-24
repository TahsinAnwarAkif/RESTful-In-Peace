
package com.management.hospital.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.hospital.model.Role;
import com.management.hospital.model.User;
import com.management.hospital.repository.RoleRepository;
import com.management.hospital.repository.UserRepository;

@RestController
public class UserController {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	public UserController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@GetMapping(Mappings.SHOW_USERS)
	public Map<String, Object> getAllUsers() {
		Map<String, Object> json = new HashMap<String, Object>();
		List<User> users = new ArrayList<User>();

		for (User user : userRepository.findAll()) {
			users.add(user);
		}
		json.put("users", users);
		return json;
	}

	@GetMapping(Mappings.SHOW_A_USER)
	public Map<String, Object> getUser(@PathVariable Long id) {
		Map<String, Object> json = new HashMap<String, Object>();
		User user = userRepository.findOne(id);

		json.put("user", user);
		return json;
	}

	@PostMapping(Mappings.ADD_A_USER)
	public Map<String, Object> addUser(@RequestBody User user) throws Exception {
		Map<String, Object> json = new HashMap<String, Object>();

		if(user.getRole().getId() == Constants.ADMIN_ROLE && roleRepository.findOne(Constants.ADMIN_ROLE) == null) {
			Role adminRole = new Role();
			adminRole.setId(Constants.ADMIN_ROLE);
			adminRole.setName(Constants.ADMIN);
			roleRepository.save(adminRole);
		}
		
		if(user.getRole().getId() == Constants.USER_ROLE && roleRepository.findOne(Constants.USER_ROLE) == null) {
			Role userRole = new Role();
			userRole.setId(Constants.USER_ROLE);
			userRole.setName(Constants.USER);
			roleRepository.save(userRole);
			}
		userRepository.save(user);

		json.put("user", user);
		return json;
	}

	@PutMapping(Mappings.UPDATE_A_USER)
	public Map<String, Object> updateUser(@RequestBody User user, @PathVariable Long id) {
		Map<String, Object> json = new HashMap<String, Object>();
		User updatedUser = userRepository.save(user);
		
		if(user.getRole().getId() == Constants.ADMIN_ROLE && roleRepository.findOne(Constants.ADMIN_ROLE) == null) {
			Role adminRole = new Role();
			adminRole.setId(Constants.ADMIN_ROLE);
			adminRole.setName(Constants.ADMIN);
			roleRepository.save(adminRole);
		}
		
		if(user.getRole().getId() == Constants.USER_ROLE && roleRepository.findOne(Constants.USER_ROLE) == null) {
			Role userRole = new Role();
			userRole.setId(Constants.USER_ROLE);
			userRole.setName(Constants.USER);
			roleRepository.save(userRole);
		}
		
		json.put("user", updatedUser);
		json.put("userUpdate", 1);
		
		return json;
	}

	@DeleteMapping(Mappings.DELETE_A_USER)
	public Map<String, Object> deleteUser(@PathVariable Long id) {
		Map<String, Object> json = new HashMap<String, Object>();
		userRepository.delete(id);

		json.put("userDelete", 1);
		return json;
	}
}
