package com.example.mbyaka.contact;

import android.text.format.Time;

import java.util.Date;

/**
 * Created by YAKA on 2.1.2016.
 */
public class PhoneCalls {

    private Date date;
    private Time time;
    private int duration;
    private Person person;

    public PhoneCalls()
    {
        date = new Date();
        time = new Time();
        duration = 0;
        person = new Person();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
