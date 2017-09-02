package com.enterprise.mse.fitdroid;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mike on 8/23/17.
 */

public class PaymentMethod {

    private UUID paymentID;
    private String nameOnPayment;
    private String cardNumber;
    private Date expirationDate;
    private String cvv;
    private String billingAddress;
    private String billingCity;
    private String billingState;
    private String billingZIP;


    public PaymentMethod(String nameOnPayment,
                         String cardNumber,
                         Date expirationDate,

                         String billingAddress,
                         String billingCity,
                         String billingState,
                         String billingZIP){
        this.nameOnPayment = nameOnPayment;
        this.cardNumber =cardNumber;
        this.expirationDate = expirationDate;

        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingZIP = billingZIP;

        this.paymentID = UUID.randomUUID();
    }

    public PaymentMethod() {
        this.paymentID = UUID.randomUUID();
    }

    public String getNameOnPayment() {
        return nameOnPayment;
    }

    public void setNameOnPayment(String nameOnPayment) {
        this.nameOnPayment = nameOnPayment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }



    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZIP() {
        return billingZIP;
    }

    public void setBillingZIP(String billingZIP) {
        this.billingZIP = billingZIP;
    }

    public UUID getPaymentID(){return paymentID;}
}
