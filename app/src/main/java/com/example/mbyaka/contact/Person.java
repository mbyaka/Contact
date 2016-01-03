package com.example.mbyaka.contact;

/**
 * Created by YAKA on 2.1.2016.
 */
public class Person {

    private String name;
    private String surName;
    private String number;
    private String eMail;
    private String location;
    private int totalDurationInComingCall;
    private int totalDurationOutGoingCall;
    private int numberOfMissingCall;
    private int numberOfSentMessage;
    private int numberOfReceivedMessage;

    public Person()
    {
        name = "";
        surName = "";
        number = "";
        eMail = "";
        location = "";
        totalDurationInComingCall = 0;
        totalDurationOutGoingCall = 0;
        numberOfMissingCall = 0;
        numberOfSentMessage = 0;
    }

    @Override
    public String toString() {
        return name + " " + surName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}
