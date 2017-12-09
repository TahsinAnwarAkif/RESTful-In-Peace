package com.management.hospital.sevice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.management.hospital.controller.PatientController;
import com.management.hospital.model.Patient;
import com.management.hospital.repository.PatientRepository;

@Service
public class PatientService {

	private PatientRepository patientRepository;
	private final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public Map<String, Set<String>> findErrorsInCreation(Patient patient, BindingResult result) {
		Map<String, Set<String>> errors = new HashMap<String, Set<String>>();
		Patient patientWithSameEmail = patientRepository.findByEmail(patient.getEmail());
		Patient patientWithSamePhone = patientRepository.findByPhone(patient.getPhone());

		if (patientWithSameEmail != null) {
			errors.computeIfAbsent("email", key -> new HashSet<>()).add("patientWithSameEmailAlreadyExists");
		}
		if (patientWithSamePhone != null) {
			errors.computeIfAbsent("phone", key -> new HashSet<>()).add("patientWithSamePhoneAlreadyExists");
		}
		for (FieldError fieldError : result.getFieldErrors()) {
			String code = fieldError.getCode();
			String field = fieldError.getField();
			if (code.equals("NotEmpty") || code.equals("NotNull")) {
				errors.computeIfAbsent(field, key -> new HashSet<>()).add("required");
			}
		}
		return errors;
	}

	public Map<String, Set<String>> findErrorsInUpdate(Patient patient, BindingResult result) {
		Map<String, Set<String>> errors = new HashMap<String, Set<String>>();
		Patient patientBeforeUpdate = patientRepository.findOne(patient.getId());

		if (!patientBeforeUpdate.getEmail().equals(patient.getEmail())) {
			Patient patientWithSameEmail = patientRepository.findByEmail(patient.getEmail());
			LOGGER.info("TEST MAIL>>>>>> "+patientWithSameEmail);
			LOGGER.info("TEST MAIL>>>>>> "+patientWithSameEmail.getEmail()+" EQUALS "+patient.getEmail());
			if (patientWithSameEmail != null) {
				errors.computeIfAbsent("email", key -> new HashSet<>()).add("patientWithSameEmailAlreadyExists");
			}
		}
		if (!patientBeforeUpdate.getPhone().equals(patient.getPhone())) {
			Patient patientWithSamePhone = patientRepository.findByPhone(patient.getPhone());
			
			if (patientWithSamePhone != null) {
				errors.computeIfAbsent("phone", key -> new HashSet<>()).add("patientWithSamePhoneAlreadyExists");
			}
		}

		for (FieldError fieldError : result.getFieldErrors()) {
			String code = fieldError.getCode();
			String field = fieldError.getField();
			if (code.equals("NotEmpty") || code.equals("NotNull")) {
				errors.computeIfAbsent(field, key -> new HashSet<>()).add("required");
			}
		}
		return errors;
	}

}
