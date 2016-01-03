package com.example.mbyaka.contact;

import java.util.ArrayList;

/**
 * Created by YAKA on 2.1.2016.
 */
public class Person {

    private int ID;
    private String name;
    private String surName;
    private String home_number;
    private String work_number;
    private String mobile_number;
    private String eMail;
    private String location;
    private int totalDurationInComingCall;
    private int totalDurationOutGoingCall;
    private int numberOfMissingCall;
    private int numberOfSentMessage;
    private int numberOfReceivedMessage;
    private ArrayList<PhoneCalls> phoneCallses;

    public Person(int ID,String name, String surName, String home_number,
                  String work_number, String mobile_number, String eMail,
                  String location, int totalDurationInComingCall, int totalDurationOutGoingCall,
                  int numberOfMissingCall, int numberOfSentMessage, int numberOfReceivedMessage,
                  ArrayList<PhoneCalls> phoneCallses) {

        this.ID = ID;
        this.name = name;
        this.surName = surName;
        this.home_number = home_number;
        this.work_number = work_number;
        this.mobile_number = mobile_number;
        this.eMail = eMail;
        this.location = location;
        this.totalDurationInComingCall = totalDurationInComingCall;
        this.totalDurationOutGoingCall = totalDurationOutGoingCall;
        this.numberOfMissingCall = numberOfMissingCall;
        this.numberOfSentMessage = numberOfSentMessage;
        this.numberOfReceivedMessage = numberOfReceivedMessage;
        this.phoneCallses = phoneCallses;
    }
    public String toString() {
        return name + " " + surName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getHome_number() {
        return home_number;
    }

    public void setHome_number(String home_number) {
        this.home_number = home_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalDurationInComingCall() {
        return totalDurationInComingCall;
    }

    public void setTotalDurationInComingCall(int totalDurationInComingCall) {
        this.totalDurationInComingCall = totalDurationInComingCall;
    }

    public int getTotalDurationOutGoingCall() {
        return totalDurationOutGoingCall;
    }

    public void setTotalDurationOutGoingCall(int totalDurationOutGoingCall) {
        this.totalDurationOutGoingCall = totalDurationOutGoingCall;
    }

    public int getNumberOfMissingCall() {
        return numberOfMissingCall;
    }

    public void setNumberOfMissingCall(int numberOfMissingCall) {
        this.numberOfMissingCall = numberOfMissingCall;
    }

    public int getNumberOfSentMessage() {
        return numberOfSentMessage;
    }

    public void setNumberOfSentMessage(int numberOfSentMessage) {
        this.numberOfSentMessage = numberOfSentMessage;
    }

    public int getNumberOfReceivedMessage() {
        return numberOfReceivedMessage;
    }

    public void setNumberOfReceivedMessage(int numberOfReceivedMessage) {
        this.numberOfReceivedMessage = numberOfReceivedMessage;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getWork_number() {
        return work_number;
    }

    public void setWork_number(String work_number) {
        this.work_number = work_number;
    }

    public ArrayList<PhoneCalls> getPhoneCallses() {
        return phoneCallses;
    }

    public void setPhoneCallses(ArrayList<PhoneCalls> phoneCallses) {
        this.phoneCallses = phoneCallses;
    }

}
