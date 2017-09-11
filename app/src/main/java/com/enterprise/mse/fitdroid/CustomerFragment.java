package com.enterprise.mse.fitdroid;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.UUID;

/**
 * Created by mikeellis on 8/26/17.
 * this is the controller for the customer info panel
 */

public class CustomerFragment extends Fragment {

    private Customer mCustomer;
    private final String TAG = "CustomerFragment";
    private final static String ARG_CUSTOMER_ID = "customerID";
    private boolean mNewCustomer;

    //UI controls
    private EditText mCustomerName;
    private EditText mCustomerEmail;
    private EditText mCustomerAddr;
    private EditText mCustomerPhone;
    private Button mSaveButton;
    private ImageView mAddImage;


    // onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null && args.containsKey(ARG_CUSTOMER_ID)) {
            UUID customerID = (UUID)args.getSerializable(ARG_CUSTOMER_ID);
            mCustomer = CustomerList.getCustomerList(getActivity()).getCustomer(customerID);
            mNewCustomer = false;
        } else {
            Log.d(TAG,"creating new customer");
            mCustomer = new Customer();
            mNewCustomer = true;
        }

    }

    // onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstance) {

        // inflate the Customer View
        View v = inflater.inflate(R.layout.fragment_customer_info,container,false);

        // get references to all UI components
        mCustomerName = (EditText) v.findViewById(R.id.customer_name_text);
        mCustomerEmail = (EditText) v.findViewById(R.id.customer_email_input);
        mCustomerAddr = (EditText) v.findViewById(R.id.customer_address_input);
        mCustomerPhone = (EditText) v.findViewById(R.id.customer_phone_input);
        mAddImage = (ImageView) v.findViewById(R.id.customer_add_img);
        mSaveButton = (Button) v.findViewById(R.id.customer_save_btn);

        // if existing customer, fill the values
        if(!mNewCustomer) {
            mCustomerName.setText(mCustomer.getCustomerName());
            mCustomerEmail.setText(mCustomer.getEmail());
            mCustomerAddr.setText(mCustomer.getAddress());
            mCustomerPhone.setText(mCustomer.getPhoneNumber());
            //TODO image
        }
        // add all the listeners
        mCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // update the customer name
                mCustomer.setCustomerName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCustomerEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCustomer.setEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCustomerAddr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCustomer.setAddress(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCustomerPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCustomer.setPhoneNumber(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // take photo button
        mAddImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG,"add image button touched");
                //TODO integrate photo taking

                return false;
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"save button clicked");

            }
        });

        return v;
    }


    //onPause:  save info to DB
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause called");
        CustomerList.getCustomerList(getActivity()).updateCustomer(mCustomer);
    }

    // return new instance using customer id
    public static CustomerFragment newInstance(UUID customerID) {
        // create a new customer fragment
        CustomerFragment fragment = new CustomerFragment();
        // if customerID supplied, place it in an args bundle
        if(customerID != null) {
            Bundle args = new Bundle();
            args.putSerializable(ARG_CUSTOMER_ID,customerID);
            fragment.setArguments(args);
        }

        return fragment;
    }
}
