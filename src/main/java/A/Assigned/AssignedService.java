package A.Assigned;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignedService {
    
    @Autowired
    private AssignedRepository dr;

        public List<Assigned> getAllAssigned() //DONE
        {  
            List<Assigned> t = new ArrayList<Assigned>();
            dr.findAll()
                    .forEach(t :: add);
            return t;
        }
        
        public Assigned getAssigned(String id)
        {
            return dr.findOne(id);
        }

    void addAssigned(Assigned d) 
    {
        dr.save(d);
    }

    void updateAssigned(Assigned d, String id) 
    {
        dr.save(d);
    }

    void deleteAssigned(String id) 
    {
        dr.delete(id);
    }
        
}
