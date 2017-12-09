
package com.management.hospital.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.validation.BindingResult;
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
import com.management.hospital.repository.DoctorRepository;
import com.management.hospital.repository.PatientRepository;
import com.management.hospital.sevice.PatientService;


@RestController
public class PatientController {
 
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;
	private PatientService patientService;
	private final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	public PatientController(PatientRepository patientRepository, DoctorRepository doctorRepository, PatientService patientService) {
		this.patientRepository = patientRepository;
		this.doctorRepository  = doctorRepository;
		this.patientService    = patientService;
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

    @GetMapping(Mappings.GET_ADD_PATIENT_FORM)
    public Map<String, Object> getAddPatientForm(){
    	Map<String, Object> json = new HashMap<String, Object>();
    	json.put("availableDoctorIds", doctorRepository.findAvailableDoctorIds());
    	return json;
    }
    
    @PostMapping(Mappings.ADD_A_PATIENT)
    public Map<String, Object> addPatient(@RequestBody Patient patient, BindingResult result)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Map<String,Set<String>> errors = new HashMap<String, Set<String>>(patientService.findErrorsInCreation(patient, result));
    	json.put("success", 0);
    	
    	if(errors.isEmpty()) {
    		
    		patientRepository.save(patient);
    		json.put("patient", patient);	
    		json.put("success", 1);
    	}
        json.put("errors", errors);
        return json;
    }
    
    @PutMapping(Mappings.UPDATE_A_PATIENT)
    public  Map<String, Object> updatePatient(@RequestBody Patient patient, BindingResult result, @PathVariable Long id)
    {
    	Map<String, Object> json = new HashMap<String, Object>();
    	Map<String,Set<String>> errors = new HashMap<String, Set<String>>(patientService.findErrorsInUpdate(patient, result));
    	json.put("success", 0);
    	
    	if(errors.isEmpty()) {
    	Patient updatedPatient = patientRepository.save(patient);
    	json.put("patient", updatedPatient);
    	json.put("patientUpdate", 1);
    	json.put("success", 1);
    	}
    	
    	json.put("errors", errors);
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
