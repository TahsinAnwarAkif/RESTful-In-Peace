package A.Doctor;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

//@PreAuthorize("hasRole('ROLE_USER')")
public interface DoctorRepository extends CrudRepository<Doctor, String> 
{
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    List<Doctor> findAll();
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    Doctor findOne(String id);
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Doctor save(Doctor s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(String id);
}
