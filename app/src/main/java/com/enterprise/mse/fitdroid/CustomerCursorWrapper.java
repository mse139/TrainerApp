package com.enterprise.mse.fitdroid;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import database.CustomerSchema.CustomerTable;


/**
 * Created by mike on 9/10/17.
 */

public class CustomerCursorWrapper extends CursorWrapper {

    public CustomerCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    //getCustomer:  return customer object from db cursor
    public Customer getCustomer(){

        // get the data
        String uuidString = getString(getColumnIndex(CustomerTable.Cols.UUID));
        String customerName = getString(getColumnIndex(CustomerTable.Cols.CUST_NAME));
        String address = getString(getColumnIndex(CustomerTable.Cols.ADDRESS));
        String email = getString(getColumnIndex(CustomerTable.Cols.EMAIL));

        //create the object
        Customer customer = new Customer(UUID.fromString(uuidString));
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setCustomerName(customerName);

        return customer;
    }
}
