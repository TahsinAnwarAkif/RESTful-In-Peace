
package A.Assigned;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignedController {
 
   @Autowired
    private AssignedService ds;
    
    //GET
    @RequestMapping("/assigned")
    public  List<Assigned> getAllAssigned() //DONE
    {
        return ds.getAllAssigned();
        
    }
    
    //GET
    @RequestMapping("/assigned/{id}")  //path, id = variable
    public Assigned getAssigned(@PathVariable String id)
    {
        return ds.getAssigned(id);
    }
    
    //POST
    @RequestMapping(method = RequestMethod.POST,value = "/assigned")
    public void addAssigned(@RequestBody Assigned d)
    {
        ds.addAssigned(d);
    }
    
    //UPDATE
    @RequestMapping(method = RequestMethod.PUT,value = "/assigned/{id}")
    public void updateAssigned(@RequestBody Assigned d, @PathVariable String id)
    {
        ds.updateAssigned(d, id );
    }
    
    @RequestMapping(method = RequestMethod.DELETE,value = "/assigned/{id}")
    public void deleteAssigned(@PathVariable String id)
    {
        ds.deleteAssigned(id );
    }
    
}
