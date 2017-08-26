package com.enterprise.mse.fitdroid;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mike on 8/23/17.
 */

public class SessionPayment {

    private UUID sessionID;
    private UUID paymentID;     // unique payment id
    private UUID paymentMethodID;
    private Date paymentDate;
    private double paymentAmount;

    // TODO: signature variable

    public SessionPayment(UUID sessionID,
                          UUID paymentMethodID,
                          Date paymentDate,
                          double paymentAmount
                          ) {
        this.sessionID = sessionID;
        this.paymentMethodID = paymentMethodID;

        this.paymentDate = paymentDate ;
        this.paymentAmount = paymentAmount;
    }

    public UUID getPaymentID(){
        return this.paymentID;
    }
    public UUID getSessionID() {
        return sessionID;
    }

    public void setSessionID(UUID sessionID) {
        this.sessionID = sessionID;
    }

    public UUID getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(UUID paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
