
package A.Doctor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    
    @Autowired
    private DoctorRepository dr;

        public List<Doctor> getAllDoctors()
        {  
            List<Doctor> t = new ArrayList<Doctor>();
            dr.findAll()
                    .forEach(t :: add);
            return t;
        }
        
        public Doctor getDoctor(String id)
        {
            return dr.findOne(id);
        }

    void addDoctor(Doctor d) 
    {
        dr.save(d);
    }

    void updateDoctor(Doctor d, String id) 
    {
        dr.save(d);
    }

    void deleteDoctor(String id) 
    {
        dr.delete(id);
    }
        
}
