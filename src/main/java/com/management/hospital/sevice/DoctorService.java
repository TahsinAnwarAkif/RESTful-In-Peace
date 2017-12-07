package com.management.hospital.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.management.hospital.model.Doctor;
import com.management.hospital.model.Patient;
import com.management.hospital.repository.PatientRepository;

@Service
public class DoctorService {

	private PatientRepository patientRepository;

	public DoctorService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
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
		List<Patient> patientsOfDoctor =  getPatientsOfSpecificDoctor(doctor.getId());
		doctor.setPatients(patientsOfDoctor);
		
		if (doctor.getPatients() != null) {
			for (Patient patient : doctor.getPatients()) {
				patientRepository.delete(patient);
			}
		}
	}

}
