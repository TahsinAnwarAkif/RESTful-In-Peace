
package A.Doctor;

import A.patient.Patient;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
    
    @Id
    private String id;
    private String name;
    private String is_available;
    private String specialty;
    private String address;
    private String phone;
    private String email;
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<Patient> patient;

    public Doctor()
    {
        
    }

    public Doctor(String d_id, String d_name, String isAvailable, String specialty, String d_address, String d_phone, String d_email) {
        this.id = d_id;
        this.name = d_name;
        this.is_available = isAvailable;
        this.specialty = specialty;
        this.address = d_address;
        this.phone = d_phone;
        this.email = d_email;
        this.patient= null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsAvailable() {
        return is_available;
    }

    public void setIsAvailable(String isAvailable) {
        this.is_available = isAvailable;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /*
    public Set<Patient> getPatient() {
        return patient;
    }
    */
    public void setPatient(Set<Patient> patient) {
        this.patient = patient;
    }
    
    
    

}
