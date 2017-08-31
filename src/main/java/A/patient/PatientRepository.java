package A.patient;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
public interface PatientRepository extends CrudRepository<Patient, String> 
{
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    List<Patient> findAll();
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    Patient findOne(String id);
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Patient save(Patient s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(String id);
   
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<Patient> findByDoctorId(String doctorId); 
}
