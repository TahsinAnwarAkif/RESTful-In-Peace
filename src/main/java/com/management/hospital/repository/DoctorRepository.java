package com.management.hospital.repository;

import org.springframework.data.repository.CrudRepository;

import com.management.hospital.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

}
