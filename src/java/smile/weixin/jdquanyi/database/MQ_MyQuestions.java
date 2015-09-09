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
@Table(name="MQ_MyQuestions")
public class MQ_MyQuestions {
    int Id;
    String fromUserName;
    String description;
    String putTime;
    String reply;
    String replytime;
    boolean tag;
    
    @Id
    public int getId() {
        return Id;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public String getDescription() {
        return description;
    }

    public String getPutTime() {
        return putTime;
    }

    public String getReply() {
        return reply;
    }

    public String getReplytime() {
        return replytime;
    }

    public boolean getTag() {
        return tag;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPutTime(String putTime) {
        this.putTime = putTime;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setReplytime(String replytime) {
        this.replytime = replytime;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }
    
    
    
}
