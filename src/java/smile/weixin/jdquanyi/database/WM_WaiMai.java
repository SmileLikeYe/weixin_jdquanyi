/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smile.weixin.jdquanyi.database;

import javax.persistence.*;

/**
 *
 * @author smile
 */

@Entity
@Table( name="WM_WaiMai" )
public class WM_WaiMai {
    int WM_id;
    String WM_name;
    String WM_telephone;
    String WM_address;
    String WM_menuLink;

    public String getWM_menuLink() {
        return WM_menuLink;
    }

    public void setWM_menuLink(String WM_menuLink) {
        this.WM_menuLink = WM_menuLink;
    }
    
    
    
    @Id
    public int getWM_id() {
        return WM_id;
    }

    public String getWM_name() {
        return WM_name;
    }

    public String getWM_telephone() {
        return WM_telephone;
    }

    public String getWM_address() {
        return WM_address;
    }

    public void setWM_id(int WM_id) {
        this.WM_id = WM_id;
    }

    public void setWM_name(String WM_name) {
        this.WM_name = WM_name;
    }

    public void setWM_telephone(String WM_telephone) {
        this.WM_telephone = WM_telephone;
    }

    public void setWM_address(String WM_address) {
        this.WM_address = WM_address;
    }
    
    
    
    
}
