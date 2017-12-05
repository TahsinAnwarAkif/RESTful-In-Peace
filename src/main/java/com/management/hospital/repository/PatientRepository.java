package com.management.hospital.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.management.hospital.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	List<Patient> findByDoctorId(Long id);
}
