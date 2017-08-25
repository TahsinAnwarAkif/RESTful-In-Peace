
package A.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository pr;

        public List<Patient> getAllPatients()
        {  
            List<Patient> t = new ArrayList<Patient>();
            pr.findAll()
                    .forEach(t :: add);
            return t;
        }
        
        public Patient getPatient(String id)
        {
            return pr.findOne(id);
        }

    void addPatient(Patient p) 
    {
        pr.save(p);
    }

    void updatePatient(Patient p, String id) 
    {
        pr.save(p);
    }

    void deletePatient(String id) 
    {
        pr.delete(id);
    }
        
}
