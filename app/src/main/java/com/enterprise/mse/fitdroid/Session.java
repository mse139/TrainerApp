package com.enterprise.mse.fitdroid;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mike on 8/23/17.
 */

public class Session {

    private UUID sessionID;
    private UUID customerID;
    private Date sessionDateTime;
    private boolean complete;
    private boolean paid;
    private String notes;
    private double cost;
    private String location;


    // default ctor
    public Session() {
        sessionID = UUID.randomUUID();
    }

    // base constructor
    public Session(UUID customerID,Date sessionDate,String location) {
        sessionID = UUID.randomUUID();
        this.customerID = customerID;
        this.sessionDateTime = sessionDate;
        this.complete = false;
        this.location = location;
        this.paid = false;
    }

    // constructor with notes
    public Session(UUID customerID,Date sessionDate,String location,String notes) {
        this(customerID,sessionDate,location);
        this.notes = notes;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public void setCustomerID(UUID customerID) {
        this.customerID = customerID;
    }

    public Date getSessionDateTime() {
        return sessionDateTime;
    }

    public void setSessionDateTime(Date sessionDateTime) {
        this.sessionDateTime = sessionDateTime;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
