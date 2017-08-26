
package A.Assigned;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Assigned {
    
     @Id
     private String a_id;
     private String d_id;
     private String p_id;
    
public Assigned()
    {
                 
    }

    public Assigned(String a_id, String d_id, String p_id) {
        this.a_id = a_id;
        this.d_id = d_id;
        this.p_id = p_id;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }
    
    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }


}
