package com.management.hospital.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.management.hospital.model.Patient;


@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	List<Patient> findByDoctorId(Long id);
	
	@Modifying
	@Query(value = "delete from patient where id = :patientId", nativeQuery=true)
	void deleteById(@Param("patientId") Long patientId);
}
