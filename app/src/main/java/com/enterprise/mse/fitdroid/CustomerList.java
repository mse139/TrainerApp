package com.enterprise.mse.fitdroid;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 8/29/17.
 */

public class CustomerList {

    // instance variable
    private static CustomerList sCustomerList;

    // list of customers
    private List<Customer> mCustomerList;

    // public interface - returns the sCustomerList
    public static CustomerList getCustomerList(Context context) {
        if(sCustomerList == null)
            sCustomerList = new CustomerList(context);
        return sCustomerList;
    }

    // private ctor
    private CustomerList(Context context) {
        mCustomerList = new ArrayList<>();

        // add two customers
        mCustomerList.add(new Customer("Customer1"));
        mCustomerList.add(new Customer("Customer 2"));
    }

    // return list of customers
    public List<Customer> getCustomers() {return mCustomerList;}

    // search the list for a customer
    public Customer getCustomer(UUID id) {
        for(Customer customer: mCustomerList) {
            if(customer.getCustomerID() == id)
                return customer;
        }
        return null;
    }

    public void addCustomer(Customer c) {
        mCustomerList.add(c);
    }
}
