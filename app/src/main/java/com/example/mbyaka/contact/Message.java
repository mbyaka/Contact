package com.example.mbyaka.contact;

import java.util.Date;

/**
 * Created by YAKA on 6.1.2016.
 */
public class Message {
    private int ID;
    private String who;
    private Boolean isComing;
    private Date date;
    private Date time;
    private String body;

    public Message(String who, Boolean isComing, Date date, Date time, String body) {
        this.who = who;
        this.isComing = isComing;
        this.date = date;
        this.time = time;
        this.body = body;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public Boolean getIsComing() {
        return isComing;
    }

    public void setIsComing(Boolean isComing) {
        this.isComing = isComing;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
