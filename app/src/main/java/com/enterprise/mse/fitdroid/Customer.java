package com.enterprise.mse.fitdroid;

import java.io.Console;
import java.util.UUID;

/**
 * Created by mike on 8/23/17.
 * Customer holds details about each customer
 */

public class Customer {

    private String customerName;
    private String phoneNumber;
    private UUID    customerID;
    private String address;
    private String email;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public UUID getCustomerID(){return this.customerID;}
    public Customer(String name, String address,String phoneNumber, String email) {
        this();
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.customerName = name;
        this.address = address;

    }

    public Customer() {
        this(UUID.randomUUID());
    }


    public Customer(UUID id) {
        this.customerID = id;
    }
    public Customer(String name) {
        this();
        this.customerName = name;
    }


}
