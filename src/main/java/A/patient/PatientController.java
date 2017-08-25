
package A.patient;

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
    
}
