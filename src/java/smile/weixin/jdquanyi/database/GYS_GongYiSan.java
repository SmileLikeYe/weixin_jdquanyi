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
@Table(name = "GongYiSan")
public class GYS_GongYiSan {
    int GYS_id;
    String GYS_name;
    String GYS_studentNumber;
    String GYS_academy;
    String GYS_fromUserName;
    boolean GYS_verification;//是否通过验证
    String GYS_frequency;
    boolean GYS_pure;//是否已经归还公益伞
    String  GYS_telephone;

    public void setGYS_telephone(String GYS_telephone) {
        this.GYS_telephone = GYS_telephone;
    }

    public boolean isGYS_verification() {
        return GYS_verification;
    }

    public boolean isGYS_pure() {
        return GYS_pure;
    }

    public String getGYS_telephone() {
        return GYS_telephone;
    }

    public void setGYS_id(int GYS_id) {
        this.GYS_id = GYS_id;
    }

    public void setGYS_name(String GYS_name) {
        this.GYS_name = GYS_name;
    }

    public void setGYS_studentNumber(String GYS_studentNumber) {
        this.GYS_studentNumber = GYS_studentNumber;
    }

    public void setGYS_academy(String GYS_academy) {
        this.GYS_academy = GYS_academy;
    }

    public void setGYS_fromUserName(String GYS_fromUserName) {
        this.GYS_fromUserName = GYS_fromUserName;
    }

    public void setGYS_verification(boolean GYS_verification) {
        this.GYS_verification = GYS_verification;
    }

    public void setGYS_frequency(String GYS_frequency) {
        this.GYS_frequency = GYS_frequency;
    }
    public void setGYS_pure(boolean GYS_pure)  {
        this.GYS_pure = GYS_pure; 
    }
 
    @Id
    public int getGYS_id() {
        return GYS_id;
    }

    public String getGYS_name() {
        return GYS_name;
    }

    public String getGYS_studentNumber() {
        return GYS_studentNumber;
    }

    public String getGYS_academy() {
        return GYS_academy;
    }

    public String getGYS_fromUserName() {
        return GYS_fromUserName;
    }

    public boolean getGYS_verification() {
        return GYS_verification;
    }

    public String getGYS_frequency() {
        return GYS_frequency;
    }
    
    public boolean getGYS_pure() {
        return GYS_pure;
    }
    
    
}
