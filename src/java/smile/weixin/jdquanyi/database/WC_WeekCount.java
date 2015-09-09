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
@Table(name = "WC_WeekCount")
public class WC_WeekCount {
    int id;
    int count;

    public void setId(int id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Id
    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
    
}
