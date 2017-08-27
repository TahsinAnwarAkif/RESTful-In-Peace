package A.patient;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
public interface PatientRepository extends CrudRepository<Patient, String> 
{
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Patient save(Patient s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(String id);
   
}
