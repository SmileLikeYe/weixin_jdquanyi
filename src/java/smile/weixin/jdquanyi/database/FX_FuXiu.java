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
@Table(name = "FX_FuXiu")
public class FX_FuXiu {
    	int fx_id;
	String fx_fromUserName;
        String fx_description;
        String fx_name;
        String fx_studentNumber;
        String fx_academy;
        int fx_count;
        int fx_flag;

    public void setFx_flag(int fx_flag) {
        this.fx_flag = fx_flag;
    }

    public int getFx_flag() {
        return fx_flag;
    }

    public void setFx_count(int fx_count) {
        this.fx_count = fx_count;
    }

    public void setFx_name(String fx_name) {
        this.fx_name = fx_name;
    }

    public void setFx_studentNumber(String fx_studentNumber) {
        this.fx_studentNumber = fx_studentNumber;
    }

    public void setFx_academy(String fx_academy) {
        this.fx_academy = fx_academy;
    }

    public String getFx_name() {
        return fx_name;
    }

    public String getFx_studentNumber() {
        return fx_studentNumber;
    }

    public String getFx_academy() {
        return fx_academy;
    }

    //统计有多少人
    public void setFx_id(int fx_id) {
        this.fx_id = fx_id;
    }

    public void setFx_fromUserName(String fx_fromUserName) {
        this.fx_fromUserName = fx_fromUserName;
    }
       
    public void setFx_description(String fx_description) {
        this.fx_description = fx_description;
    }

    @Id
    public int getFx_id() {
        return fx_id;
    }

    
    public String getFx_fromUserName() {
        return fx_fromUserName;
    }

    public String getFx_description() {
        return fx_description;
    }
    
    
    public int getFx_count() {
        return fx_count;
    }


}
