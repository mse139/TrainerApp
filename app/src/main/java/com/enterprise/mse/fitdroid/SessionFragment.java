package com.enterprise.mse.fitdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static final int REQUEST_SESSION_DATE = 0;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_TIME = 1;
    private static final int REQUEST_COMPLETE_DATE = 2;
    private SimpleDateFormat mTimeFormat;

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
    private TextView mSessionCompleteDate;

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

        // setup the time format
        mTimeFormat = new SimpleDateFormat("h:mm a");

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
        mSessionCompleteDate = (TextView) v.findViewById(R.id.session_date_complete_text);

        if (!newSession){
            // set values if mSession has them
            setDate();
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

                showDatePicker(REQUEST_SESSION_DATE);
                return false;
            }
        });

        mTimePicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d(TAG,"mTimePicker touched");
                FragmentManager fm = getFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mSession.getSessionDateTime());
                // set the target to get the resulting time
                dialog.setTargetFragment(SessionFragment.this,REQUEST_TIME);
                dialog.show(fm,DIALOG_TIME);
                return false;
            }
        });

        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"mPayButton clicked");

                // create the payment session and show the screen
                SessionPaymentFragment fragment = SessionPaymentFragment.newInstance(mSession.getSessionID());
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_fragment_container,fragment);
                ft.commit();
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

        mSessionCompleteDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // show the date picker
                showDatePicker(REQUEST_COMPLETE_DATE);
                return false;
            }
        });





        return v;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
            return;

        if(requestCode == REQUEST_SESSION_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mSession.setSessionDateTime(date);
            setDate();

        }
        if(requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mSession.setSessionDateTime(date);
            setTime();

        }
        if(requestCode == REQUEST_COMPLETE_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mSession.setComplete(true);
            mSession.setCompletedDate(date);
            mSessionCompleteDate.setText(mSession.getCompletedDate());
        }
    }

    private void setDate() {
        mDatePicker.setText(mSession.getSessionDate());
    }

    private void setTime() {
        mTimePicker.setText(mTimeFormat.format(mSession.getSessionDateTime()));
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

    private void showDatePicker(int request) {
        FragmentManager fm = getFragmentManager();
        DatePickerFragment dialog =  DatePickerFragment.newInstance(mSession.getSessionDateTime());
        // set sessionFragment as the target to get the result
        dialog.setTargetFragment(SessionFragment.this,request);
        dialog.show(fm,DIALOG_DATE);

    }
}
