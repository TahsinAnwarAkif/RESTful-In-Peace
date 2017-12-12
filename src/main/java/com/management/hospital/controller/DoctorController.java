
package com.management.hospital.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.hospital.model.Doctor;
import com.management.hospital.model.Patient;
import com.management.hospital.model.User;
import com.management.hospital.repository.DoctorRepository;
import com.management.hospital.repository.UserRepository;
import com.management.hospital.sevice.DoctorService;

@RestController
public class DoctorController {
 
	private DoctorRepository doctorRepository;
	private DoctorService doctorService;
	private UserRepository userRepository;
	private final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);
    
	public DoctorController(DoctorRepository doctorRepository, DoctorService doctorService, UserRepository userRepository) {
		this.doctorRepository = doctorRepository;
		this.doctorService    = doctorService;
		this.userRepository   = userRepository;
	}
    
    //GET
    @GetMapping(Mappings.SHOW_DOCTORS)
    public  Map<String, Object> showAllDoctors(HttpServletRequest request)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	
    	List<Doctor> doctors = new ArrayList<Doctor>();
    	
    	for(Doctor doctor: doctorRepository.findAll()) {
    		List<Patient> patientsOfDoctor =  doctorService.getPatientsOfSpecificDoctor(doctor.getId());
    		doctor.setPatients(patientsOfDoctor);
    		doctors.add(doctor);
    	}
    	json.put("doctors", doctors);
		return json;
    }
    
    @GetMapping(Mappings.SHOW_A_DOCTOR) 
    public Map<String, Object> getDoctor(@PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Doctor doctor = doctorRepository.findOne(id);
    	List<Patient> patientsOfDoctor =  doctorService.getPatientsOfSpecificDoctor(doctor.getId());
		doctor.setPatients(patientsOfDoctor);
		
    	json.put("doctor", doctor);
        return json;
    }
    
    @PostMapping(Mappings.ADD_A_DOCTOR)
    public  Map<String, Object> addDoctor(@RequestBody Doctor doctor, BindingResult result)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Map<String, Set<String>> errors = new HashMap<String, Set<String>>(doctorService.findErrorsInCreation(doctor, result));
    	json.put("successs", 0);
    	LOGGER.info("REQUEST BODY>>>>>>>>>> "+doctor);
    	if (errors.isEmpty()) {
    	doctorRepository.save(doctor);
    	doctorService.saveDoctorsPatients(doctor);
    	json.put("doctor", doctor);
    	json.put("success", 1);    	
    	}

    	json.put("errors", errors);
    	return json;
    }

    @PutMapping(Mappings.UPDATE_A_DOCTOR)
    public  Map<String, Object> updateDoctor(@RequestBody Doctor doctor, BindingResult result, @PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Map<String, Set<String>> errors = new HashMap<String, Set<String>>(doctorService.findErrorsInUpdate(doctor, result));
    	json.put("success",0);
    	
    	if(errors.isEmpty()) {
    	Doctor updatedDoctor = doctorRepository.save(doctor);
    	doctorService.saveDoctorsPatients(doctor);
    	json.put("doctor", updatedDoctor);
    	json.put("doctorUpdate", 1);
    	json.put("success", 1 );
    	}
    	
    	json.put("errors", errors );
    	return json;
    }
    
    @DeleteMapping(Mappings.DELETE_A_DOCTOR)
    public Map<String, Object> deleteDoctor(@PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	
    	Doctor doctor = doctorRepository.findOne(id);
    	doctorService.deleteDoctorsPatients(doctor);
    	
    	doctorRepository.delete(id);
    	
    	json.put("doctorDelete", 1);
    	return json;
    }
}
