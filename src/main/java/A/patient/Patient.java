
package A.patient;

import A.Doctor.Doctor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Patient {
    
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    public Patient()
    {
        
    }

    public Patient(String p_id, String p_name, String p_address, String p_phone, String p_email, String d_id) {
        this.id = p_id;
        this.name = p_name;
        this.address = p_address;
        this.phone = p_phone;
        this.email = p_email;
        this.doctor = new Doctor(d_id,"","","","","","");
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    

   

}
