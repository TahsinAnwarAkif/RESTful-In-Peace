package A.Assigned;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
public interface AssignedRepository extends CrudRepository<Assigned, String> 
{
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    List<Assigned> findAll();
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    Assigned findOne(String id);
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    Assigned save(Assigned s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(String id);
  
}
