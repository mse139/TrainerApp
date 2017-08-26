package com.enterprise.mse.fitdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by mike on 8/24/17.
 * This is the Session controller
 * It will show the details of a single session and accept user changes
 */

public class SessionFragment extends Fragment {
    // session instance
    private Session mSession;

    private static final String TAG = "SessionFragment";

    // UI controls
    private TextView mDatePicker;
    private TextView mTimePicker;
    private Button  mPayButton;
    private Button  mCancelButton;
    private Button mActionButton;
    private Spinner mCustomer;
    private EditText mNotes;

    // onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSession = new Session();
    }


    // onCreateView - inflate the Session UI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle onSavedInstance) {
        // inflate the session view
        View v = inflater.inflate(R.layout.fragment_session,container,false);

        // get references to all UI components
        mDatePicker = (TextView) v.findViewById(R.id.session_date);
        mTimePicker = (TextView)v.findViewById(R.id.session_time);
        mPayButton = (Button) v.findViewById(R.id.session_payment_btn);
        mCancelButton = (Button) v.findViewById(R.id.session_cancel_btn);
        mActionButton = (Button) v.findViewById(R.id.session_action_button);
        mCustomer   = (Spinner) v.findViewById(R.id.session_customer_spinner);
        mNotes = (EditText)v.findViewById(R.id.session_notes);

        // add listeners to components
        mDatePicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG,"mDatePicker touched");
                //TODO - show calendar
                return false;
            }
        });

        mTimePicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG,"mTimePicker touched");

                //TODO show time picker
                return false;
            }
        });

        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"mPayButton clicked");

                //TODO show the payment screen
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"mCancelButton clicked");
            }
        });

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"mActionButton clicked");
            }
        });

        mNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG,"Notes: onTextChanged");
                mSession.setNotes(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // nothing
            }
        });






        return v;


    }
}
