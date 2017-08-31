
package A.patient;

import A.Doctor.Doctor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
 
   @Autowired
    private PatientService ps;
    
    //GET
    @RequestMapping("/patients")
    public  List<Patient> getAllPatient()
    {
        return ps.getAllPatients();
        
    }
 
    //GET
    @RequestMapping("/patients/{id}")  //path, id = variable
    public Patient getPatient(@PathVariable String id)
    {
        return ps.getPatient(id);
    }
    
    //POST
    @RequestMapping(method = RequestMethod.POST,value = "/patients")
    public void addPatient(@RequestBody Patient p)
    {
        ps.addPatient(p);
    }
    
    //UPDATE
    @RequestMapping(method = RequestMethod.PUT,value = "/patients/{id}")
    public void updatePatient(@RequestBody Patient p, @PathVariable String id)
    {
        ps.updatePatient(p, id );
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/patients/{id}")
    public void deletePatient(@PathVariable String id)
    {
        ps.deletePatient(id );
    }
    
    //GET
    @RequestMapping("/doctors/{id}/patients")
    public  List<Patient> getAllPatientByDoctorID(@PathVariable String id) //DONE
    {
        return ps.getAllPatientByDoctorID(id); 
    }
    
    //GET
    @RequestMapping("/doctors/{doctorId}/patients/{id}")  //DONE
    public  Patient getAPatientByDoctorID(@PathVariable String id)
    {
        return ps.getPatient(id); 
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/doctors/{doctorId}/patients")
    public void addPatientInDoctor(@RequestBody Patient p, @PathVariable String doctorId)  //DONE
    {
        p.setDoctor(new Doctor(doctorId,"","","","","",""));
        ps.addPatient(p);
    }
    
    @RequestMapping(method = RequestMethod.PUT,value = "/doctors/{doctorId}/patients/{id}")
    public void updatePatientInDoctor(@RequestBody Patient p, @PathVariable String doctorId,@PathVariable String id) //DONE
    {
        p.setDoctor(new Doctor(doctorId,"","","","","",""));
        ps.updatePatient(p,id);
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/doctors/{doctorId}/patients/{id}")
    public void deletePatientInDoctor(@PathVariable String id)
    {
        ps.deletePatient(id);
    }
    
}
