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

import java.util.UUID;

/**
 * Created by mike on 8/24/17.
 * This is the Session controller
 * It will show the details of a single session and accept user changes
 */

public class SessionFragment extends Fragment {
    // session instance
    private Session mSession;

    private static final String TAG = "SessionFragment";
    private static final String ARG_SESSION_ID = "sessionID";

    // UI controls
    private TextView mDatePicker;
    private TextView mTimePicker;
    private Button  mPayButton;
    private Button  mCancelButton;
    private Button mActionButton;
    private Spinner mCustomer;
    private EditText mNotes;
    private EditText mLocation;
    private boolean newSession;

    // onCreate method
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"oncCreate called");
        // check for arguments
        Bundle args = getArguments();

        if(args != null && args.containsKey(ARG_SESSION_ID)) {
            // this is an existing session
            // get the session id argument
            UUID sessionID = (UUID)getArguments().getSerializable(ARG_SESSION_ID);
            Log.d(TAG,"session to get " + sessionID);
            mSession = SessionList.getSessionList(getActivity()).getSession(sessionID);
            Log.d(TAG,"session retrieved " + mSession.getSessionID());
            newSession = false;

        } else{
            // new session, so create a blank session
            mSession = new Session();
            newSession = true;
        }

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
        mLocation  = (EditText)v.findViewById(R.id.session_location_text);

        if (!newSession){
            // set values if mSession has them
            mDatePicker.setText(mSession.getSessionDate());
            mNotes.setText(mSession.getNotes());
            mLocation.setText(mSession.getLocation());
        }


        // set the action button label
        if(newSession)
            mActionButton.setText(R.string.action_btn_create);
        else
            mActionButton.setText(R.string.action_btn_update);

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

        mLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mSession.setLocation(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        return v;


    }

    // newInstance - returns a new session with the sessionID
    public static SessionFragment newInstance(UUID sessionID) {

        SessionFragment fragment = new SessionFragment();
        if(sessionID != null) {
            Bundle args = new Bundle();
            args.putSerializable(ARG_SESSION_ID, sessionID);
            fragment.setArguments(args);
        }

        return fragment;
    }
}
