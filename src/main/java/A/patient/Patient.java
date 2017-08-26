
package A.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
    
    @Id
    private String p_id;
    private String p_name;
    private String p_address;
    private String p_phone;
    private String p_email;

    public Patient()
    {
        
    }

    public Patient(String p_id, String p_name, String p_address, String p_phone, String p_email) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_address = p_address;
        this.p_phone = p_phone;
        this.p_email = p_email;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public String getP_phone() {
        return p_phone;
    }

    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
    }

    public String getP_email() {
        return p_email;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

   

}
