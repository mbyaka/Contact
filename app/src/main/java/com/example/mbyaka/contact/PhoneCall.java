package com.example.mbyaka.contact;

import java.util.Date;

/**
 * Created by YAKA on 2.1.2016.
 */
public class PhoneCall {

    private int ID;
    private Date date;
    private Date time;
    private int duration;
    private Boolean isComingCalls;
    private Boolean isMissingCalls;
    private int personID;

    public PhoneCall(Date date, Date time, int duration, Boolean isComingCalls, Boolean isMissingCalls, int personID) {
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.isComingCalls = isComingCalls;
        this.isMissingCalls = isMissingCalls;
        this.personID = personID;
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

    public Boolean getIsComingCalls() {
        return isComingCalls;
    }

    public void setIsComingCalls(Boolean isComingCalls) {
        this.isComingCalls = isComingCalls;
    }
}
