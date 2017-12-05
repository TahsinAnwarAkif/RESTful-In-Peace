
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.management.hospital.model.Patient;
import com.management.hospital.repository.PatientRepository;


@RestController
public class PatientController {
 
	private PatientRepository patientRepository;
	private final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	public PatientController(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

    @GetMapping(Mappings.SHOW_PATIENTS)
    public  Map<String, Object> getAllPatients()
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	List<Patient> patients = new ArrayList<Patient>();
    	
    	for(Patient patient: patientRepository.findAll() ) {
    		patients.add(patient);
    	}
    	json.put("patients", patients);
		return json; 
    }

    @GetMapping(Mappings.SHOW_A_PATIENT)
    public Map<String, Object> getPatient(@PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Patient patient = patientRepository.findOne(id);
    	LOGGER.info("TEST DOCTOR ID WHEN SHOWING:>>>>>>>>>>>>> "+patient.getDoctor().getId());
    	
    	json.put("patient",patient);
    	return json;
    }

    @PostMapping(Mappings.ADD_A_PATIENT)
    public Map<String, Object> addPatient(@RequestBody Patient patient)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
        
    	LOGGER.info("TEST DOCTOR ID:>>>>>>>>>>>>> "+patient.getDoctor().getId());
    	//patient.setDoctor(new Doctor(patient.getDoctor().getId()));
    	patientRepository.save(patient);
        
        json.put("patient", patient);
        return json;
    }
    
    @PutMapping(Mappings.UPDATE_A_PATIENT)
    public  Map<String, Object> updatePatient(@RequestBody Patient patient, @PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	LOGGER.info("ADDRESS: "+patient.getAddress());
    	Patient updatedPatient = patientRepository.save(patient);
    	
    	json.put("patient", updatedPatient);
    	json.put("patientUpdate", 1);
    	return json; 
    }
    
   	@DeleteMapping(Mappings.DELETE_A_PATIENT)
    public Map<String, Object> deletePatient(@PathVariable Long id)
    {
   		Map<String, Object> json = new HashMap<String, Object>();
   		patientRepository.delete(id);
   		
   		json.put("patientDelete", 1);
   		return json;
    } 
}
