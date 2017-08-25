
package A.Doctor;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {
 
   @Autowired
    private DoctorService ds;
    
    //GET
    @RequestMapping("/doctors")
    public  List<Doctor> getAllDoctors()
    {
        return ds.getAllDoctors();
        
    }
    
    //GET
    @RequestMapping("/doctors/{id}")  //path, id = variable
    public Doctor getDoctor(@PathVariable String id)
    {
        return ds.getDoctor(id);
    }
    
    //POST
    @RequestMapping(method = RequestMethod.POST,value = "/doctors")
    public void addDoctor(@RequestBody Doctor d)
    {
        ds.addDoctor(d);
    }
    
    //UPDATE
    @RequestMapping(method = RequestMethod.PUT,value = "/doctors/{id}")
    public void updateDoctor(@RequestBody Doctor d, @PathVariable String id)
    {
        ds.updateDoctor(d, id );
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/doctors/{id}")
    public void deleteDoctor(@PathVariable String id)
    {
        ds.deleteDoctor(id );
    }
    
}
