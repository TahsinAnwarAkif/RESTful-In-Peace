package com.management.hospital.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.management.hospital.model.Doctor;

@Transactional
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	@Modifying
	@Query(value = "select id from doctor", nativeQuery=true)	
	List<Long> findAvailableDoctorIds();
}
