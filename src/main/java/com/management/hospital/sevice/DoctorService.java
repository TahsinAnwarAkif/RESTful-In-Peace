package com.management.hospital.sevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.management.hospital.model.Doctor;
import com.management.hospital.model.Patient;
import com.management.hospital.repository.DoctorRepository;
import com.management.hospital.repository.PatientRepository;

@Service
public class DoctorService {

	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;

	public DoctorService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

	public List<Patient> getPatientsOfSpecificDoctor(Long doctorId) {
		List<Patient> patientsOfDoctor = new ArrayList<Patient>();
		for (Patient patient : patientRepository.findByDoctorId(doctorId)) {
			patient = new Patient(patient);
			patientsOfDoctor.add(patient);
		}
		return patientsOfDoctor;
	}

	public void saveDoctorsPatients(Doctor doctor) {
		if (doctor.getPatients() != null) {
			for (Patient patient : doctor.getPatients()) {
				patient.setDoctor(new Doctor(doctor));
				patientRepository.save(patient);
			}
		}
	}

	public void deleteDoctorsPatients(Doctor doctor) {
		List<Patient> patientsOfDoctor = getPatientsOfSpecificDoctor(doctor.getId());
		doctor.setPatients(patientsOfDoctor);

		if (doctor.getPatients() != null) {
			for (Patient patient : doctor.getPatients()) {
				patientRepository.delete(patient);
			}
		}
	}

	public Map<String, Set<String>> findErrorsInCreation(Doctor doctor, BindingResult result) {
		Map<String, Set<String>> errors = new HashMap<String, Set<String>>();
		Doctor doctorWithSameEmail = doctorRepository.findByEmail(doctor.getEmail());
		Doctor doctorWithSamePhone = doctorRepository.findByPhone(doctor.getPhone());

		if (doctorWithSameEmail != null) {
			errors.computeIfAbsent("email", key -> new HashSet<>()).add("doctorWithSameEmailAlreadyExists");
		}
		if (doctorWithSamePhone != null) {
			errors.computeIfAbsent("phone", key -> new HashSet<>()).add("doctorWithSamePhoneAlreadyExists");
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

	public Map<String, Set<String>> findErrorsInUpdate(Doctor doctor, BindingResult result) {
		Map<String, Set<String>> errors = new HashMap<String, Set<String>>();
		Doctor doctorBeforeUpdate = doctorRepository.findOne(doctor.getId());

		if (!doctorBeforeUpdate.getEmail().equals(doctor.getEmail())) {
			Doctor doctorWithSameEmail = doctorRepository.findByEmail(doctor.getEmail());
			
			if (doctorWithSameEmail != null) {
				errors.computeIfAbsent("email", key -> new HashSet<>()).add("doctorWithSameEmailAlreadyExists");
			}
		}
		if (!doctorBeforeUpdate.getPhone().equals(doctor.getPhone())) {
			Doctor doctorWithSamePhone = doctorRepository.findByPhone(doctor.getPhone());
			
			if (doctorWithSamePhone != null) {
				errors.computeIfAbsent("phone", key -> new HashSet<>()).add("doctorWithSamePhoneAlreadyExists");
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
