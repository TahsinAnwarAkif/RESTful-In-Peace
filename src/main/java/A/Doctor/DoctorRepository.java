package A.Doctor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

//@PreAuthorize("hasRole('ROLE_USER')")
public interface DoctorRepository extends CrudRepository<Doctor, String> 
{
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Doctor save(Doctor s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(String id);
}
