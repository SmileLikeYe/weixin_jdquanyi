/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.database;

/**
 *
 * @author smile
 */
import javax.persistence.*;

@Entity
@Table(name = "SW_ShiWu")
public class SW_ShiWu {
    int sw_id;
    String sw_type;
    String sw_decsription;
    String sw_place;

    public void setSw_id(int sw_id) {
        this.sw_id = sw_id;
    }

    public void setSw_type(String sw_type) {
        this.sw_type = sw_type;
    }

    public void setSw_decsription(String sw_decsription) {
        this.sw_decsription = sw_decsription;
    }

    public void setSw_place(String sw_place) {
        this.sw_place = sw_place;
    }

    
    @Id
    public int getSw_id() {
        return sw_id;
    }

    public String getSw_type() {
        return sw_type;
    }

    public String getSw_decsription() {
        return sw_decsription;
    }

    public String getSw_place() {
        return sw_place;
    }
   
    
   
    
}
