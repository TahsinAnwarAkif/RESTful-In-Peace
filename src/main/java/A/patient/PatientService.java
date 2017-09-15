
package A.patient;

import com.sun.org.apache.xpath.internal.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository pr;

        public List<Patient> getAllPatients() //DONE
        {  
            List<Patient> t = new ArrayList<Patient>();
            pr.findAll()
                    .forEach(t :: add);
            return t;
        }
        
        public Patient getPatient(String id) //DONE
        {
            return pr.findOne(id);
        }

    void addPatient(Patient p) 
    {
        pr.save(p);
    }

    void updatePatient(Patient p, String id) //DONE
    {
        pr.save(p);
    }

    void deletePatient(String id) //DONE
    {
        pr.delete(id);
    }
    
    List<Patient> getAllPatientByDoctorID(String d_id) 
    {
       List<Patient> t = new ArrayList<Patient>();
            pr.findByDoctorId(d_id)
                    .forEach(t :: add );
            return t;  
    }
    
    Patient getAPatientByDoctorID(String doctorId, String id)
    {
        List<Patient> t = getAllPatientByDoctorID(doctorId);
        
        for(int i = 0; i < t.size(); i++)
        {
            Patient singlePatient = (Patient) t.get(i);
            
            if(singlePatient.getId().equals(id)) return pr.findOne(id);
        
        }
        return null;
    }
    
    void deletePatientInDoctor(String doctorId, String id)
    {
        List<Patient> t = getAllPatientByDoctorID(doctorId);
        
        for(int i = 0; i < t.size(); i++)
        {
            Patient singlePatient = (Patient) t.get(i);
            
            if(singlePatient.getId().equals(id)) pr.delete(id);
        
        }    
    }
      
}
