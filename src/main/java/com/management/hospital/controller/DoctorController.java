
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

import com.management.hospital.model.Doctor;
import com.management.hospital.repository.DoctorRepository;

@RestController
public class DoctorController {
 
	private DoctorRepository doctorRepository;
    
	public DoctorController(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
    
    //GET
    @GetMapping(Mappings.SHOW_DOCTORS)
    public  Map<String, Object> showAllDoctors()
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	
    	List<Doctor> doctors = new ArrayList<Doctor>();
    	doctorRepository.findAll().forEach(doctors::add);
  
    	json.put("doctors", doctors);
		return json;
    }
    
    @GetMapping(Mappings.SHOW_A_DOCTOR) 
    public Map<String, Object> getDoctor(@PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Doctor doctor = doctorRepository.findOne(id);
    	
    	json.put("doctor", doctor);
        return json;
    }
    
    @PostMapping(Mappings.ADD_A_DOCTOR)
    public  Map<String, Object> addDoctor(@RequestBody Doctor doctor)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	doctorRepository.save(doctor);
    	
    	json.put("doctor", doctor);
    	return json;
    }
    
    //UPDATE
    @PutMapping(Mappings.UPDATE_A_DOCTOR)
    public  Map<String, Object> updateDoctor(@RequestBody Doctor doctor, @PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Doctor updatedDoctor = doctorRepository.save(doctor);
    	
    	json.put("doctor", updatedDoctor);
    	json.put("doctorUpdate", 1);
    	return json;
    }
    
    @DeleteMapping(Mappings.DELETE_A_DOCTOR)
    public Map<String, Object> deleteDoctor(@PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	doctorRepository.delete(id);
    	
    	json.put("doctorDelete", 1);
    	return json;
    }
}
