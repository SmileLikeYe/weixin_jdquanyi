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
@Table(name = "MCC_MenuClickCount")
public class MCC_MenuClickCount {
    
    int id;
    String name;

    int history_count;
    int monday_count;
    int tuesday_count;
    int wednesday_count;
    int thursday_count;
    int friday_count;
    int saturday_count;
    int sunday_count;

    @Id
    public int getId() {
        return id;
    }

    public int getHistory_count() {
        return history_count;
    }

    public int getMonday_count() {
        return monday_count;
    }

    public int getTuesday_count() {
        return tuesday_count;
    }

    public int getWednesday_count() {
        return wednesday_count;
    }

    public int getThursday_count() {
        return thursday_count;
    }

    public int getFriday_count() {
        return friday_count;
    }

    public int getSaturday_count() {
        return saturday_count;
    }

    public int getSunday_count() {
        return sunday_count;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setHistory_count(int history_count) {
        this.history_count = history_count;
    }

    public void setMonday_count(int monday_count) {
        this.monday_count = monday_count;
    }

    public void setTuesday_count(int tuesday_count) {
        this.tuesday_count = tuesday_count;
    }

    public void setWednesday_count(int wednesday_count) {
        this.wednesday_count = wednesday_count;
    }

    public void setThursday_count(int thursday_count) {
        this.thursday_count = thursday_count;
    }

    public void setFriday_count(int friday_count) {
        this.friday_count = friday_count;
    }

    public void setSaturday_count(int saturday_count) {
        this.saturday_count = saturday_count;
    }

    public void setSunday_count(int sunday_count) {
        this.sunday_count = sunday_count;
    }
    
    
}
