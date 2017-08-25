
package A.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
        
        private List<Patient> L = new ArrayList<Patient> (Arrays.asList(
                new Patient("01","Sabit Hasan","Dhanmondi, Dhaka","01720912970","sabit@gmail.com"),
                new Patient("02","Jubayer Al Mahmud","Hatirpool, Dhaka","01730912971","fahim@gmail.com"),
                new Patient("03","Shamsul Islam","Gulshan, Dhaka","01740912973","islam@gmail.com"),
                new Patient("04","Jawad Iqbal","Banani, Dhaka","01750912974","jawad@gmail.com"),
                new Patient("05","Rubel Rony","Farmgate, Dhaka","01716912975","rony@gmail.com")
        ));
        
     
        public List<Patient> getAllPatients()
        {  
            return L;
        }
        
        public Patient getPatient(String id)
        {
            return L.stream().filter(t -> t.getP_id().equals(id)).findFirst().get();
        }

    void addPatient(Patient p) 
    {
        L.add(p);
    }

    void updatePatient(Patient p, String id) 
    {
        for(int i = 0; i < L.size(); i++)
        {
            Patient a = (Patient)L.get(i);
            
            if(a.getP_id().equals(id))
            {
                L.set(i, p);
                return;
            }
        }
    
    }

    void deletePatient(String id) 
    {
        L.removeIf(t -> t.getP_id().equals(id));
    
    }
        
}
