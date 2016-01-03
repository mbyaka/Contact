package com.example.mbyaka.contact;

import android.text.format.Time;

import java.util.Date;

/**
 * Created by YAKA on 2.1.2016.
 */
public class PhoneCalls {

    private int ID;
    private Date date;
    private Date time;
    private int duration;
    private Boolean isMissingCalls;
    private int personID;

    public PhoneCalls(Date date,Date time,int duration,Boolean isMissingCalls)
    {
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.isMissingCalls = isMissingCalls;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getIsMissingCalls() {
        return isMissingCalls;
    }

    public void setIsMissingCalls(Boolean isMissingCalls) {
        this.isMissingCalls = isMissingCalls;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }


}
