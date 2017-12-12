package com.management.hospital.repository;


import org.springframework.data.repository.CrudRepository;

import com.management.hospital.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByName(String name);
}
