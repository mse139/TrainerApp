package com.enterprise.mse.fitdroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.CustomerBaseHelper;
import database.CustomerSchema;

/**
 * Created by mike on 8/29/17.
 */

public class CustomerList {

    // instance variable
    private static CustomerList sCustomerList;
    private static final String TAG = "CustomerList";

    // list of customers
   // private List<Customer> mCustomerList;
    // database
    private SQLiteDatabase mCustomerDB;
    private Context mContext;

    // public interface - returns the sCustomerList
    public static CustomerList getCustomerList(Context context) {
        if(sCustomerList == null)
            sCustomerList = new CustomerList(context);
        return sCustomerList;
    }

    // private ctor
    private CustomerList(Context context) {
        // get the context
        mContext = context.getApplicationContext();

        // get the database reference
        mCustomerDB = new CustomerBaseHelper(mContext).getWritableDatabase();


    }

    // return list of customers
    public List<Customer> getCustomers() {

        List<Customer> customers = new ArrayList<>();
        CustomerCursorWrapper cursor = queryCustomers(null,null);

        // iterate thru the customer cursor to add to thelist
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Log.d(TAG,"adding a customer to list");
                customers.add(cursor.getCustomer());
                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }


        return customers;}

    //update a customer
    public void updateCustomer(Customer customer) {
        Log.d(TAG,"updateCustomer called for : "+ customer.getCustomerID().toString());


        ContentValues values = getContentValues(customer);

        String idString = customer.getCustomerID().toString();

      int result = mCustomerDB.update(CustomerSchema.CustomerTable.NAME,
                values,
                CustomerSchema.CustomerTable.Cols.UUID+"=?",
                new String[] {idString});

        String text = (result == 0) ? mContext.getString(R.string.save_failure) :
                mContext.getString(R.string.save_success);

        Toast.makeText(mContext,text,Toast.LENGTH_SHORT)
                .show();

    }

    // search the list for a customer
    public Customer getCustomer(UUID id) {

        //query the db for the customer
       CustomerCursorWrapper cursor = queryCustomers(CustomerSchema.CustomerTable.Cols.UUID + "=?",
               new String[]{id.toString()});

        // try to retrieve the results
        try {
            if(cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getCustomer();
        } finally {
            cursor.close();
        }

    }

    public void addCustomer(Customer c) {

        ContentValues values = getContentValues(c);
        mCustomerDB.insert(CustomerSchema.CustomerTable.NAME,null,values);

    }

    // query the database for all customers
    public CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs) {
        Cursor cursor = mCustomerDB.query(
                CustomerSchema.CustomerTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,null,null
        );
        return new CustomerCursorWrapper(cursor);

    }

    //build a content values bundle
    public static ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();

        values.put(CustomerSchema.CustomerTable.Cols.UUID,customer.getCustomerID().toString());
        values.put(CustomerSchema.CustomerTable.Cols.ADDRESS,customer.getAddress());
        values.put(CustomerSchema.CustomerTable.Cols.CUST_NAME,customer.getCustomerName());
        values.put(CustomerSchema.CustomerTable.Cols.EMAIL,customer.getEmail());
        values.put(CustomerSchema.CustomerTable.Cols.PHONE_NUMBER,customer.getPhoneNumber());

        return values;
    }

    public File getPhotoFile(Customer customer ) {

        File fileDir = mContext.getFilesDir();
        return new File(fileDir,customer.getPhotoFileName());

    }


}
