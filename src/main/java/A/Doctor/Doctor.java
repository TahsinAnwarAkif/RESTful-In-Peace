
package A.Doctor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Doctor {
    
    @Id
    private String d_id;
    private String d_name;
    private String is_available;
    private String specialty;
    private String d_address;
    private String d_phone;
    private String d_email;

    public Doctor()
    {
        
    }

    public Doctor(String d_id, String d_name, String isAvailable, String specialty, String d_address, String d_phone, String d_email) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.is_available = isAvailable;
        this.specialty = specialty;
        this.d_address = d_address;
        this.d_phone = d_phone;
        this.d_email = d_email;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
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

    public String getD_address() {
        return d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public String getD_phone() {
        return d_phone;
    }

    public void setD_phone(String d_phone) {
        this.d_phone = d_phone;
    }

    public String getD_email() {
        return d_email;
    }

    public void setD_email(String d_email) {
        this.d_email = d_email;
    }
    

}
