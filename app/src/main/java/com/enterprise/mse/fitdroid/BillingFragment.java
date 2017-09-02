package com.enterprise.mse.fitdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mike on 8/28/17.
 * this is the controller for the payment method panel
 */

public class BillingFragment extends Fragment {

    private PaymentMethod mPaymentMethod;
    private final String TAG = "BillingFragment";

    //ui controls
    private EditText mNameOnCard;
    private EditText mBillingAddress;
    private EditText mBillingCity;
    private EditText mBillingState;
    private EditText mBillingZIP;
    private EditText mCardNumber;
    private EditText mExpirationDate;
    private Button mSaveBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create new payment method
        mPaymentMethod = new PaymentMethod();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG,"BillingFragment onCreateView");
        View v = inflater.inflate(R.layout.fragment_billing,container,false);

        // get all UI references
        mNameOnCard = (EditText) v.findViewById(R.id.billing_name);
        mBillingAddress = (EditText)v.findViewById(R.id.billing_address);
        mBillingCity = (EditText)  v.findViewById(R.id.billing_city);
        mBillingState = (EditText)  v.findViewById(R.id.billing_state);
        mBillingZIP = (EditText) v.findViewById(R.id.billing_zip);
        mCardNumber = (EditText) v.findViewById(R.id.billing_card_number);
        mExpirationDate = (EditText) v.findViewById(R.id.billing_expiration_date);
        mSaveBtn = (Button) v.findViewById(R.id.billing_save_btn);


        // add all the listeners

        // change of name on card
        mNameOnCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mPaymentMethod.setNameOnPayment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // change of billing address
        mBillingAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPaymentMethod.setBillingAddress(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // change of city
        mBillingCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPaymentMethod.setBillingCity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // change of state
        mBillingState.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                  //  mPaymentMethod.setBillingState(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0 || s.length() > 2) {
                    Toast.makeText(getActivity(), "Invalid State Entered", Toast.LENGTH_SHORT).show();
                    s.clear();
                } else
                    mPaymentMethod.setBillingState(s.toString());
            }
        });


        return v;
    }
}
