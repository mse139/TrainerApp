package com.enterprise.mse.fitdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.util.UUID;

/**
 * Created by mike on 9/5/17.
 */

public class SessionPaymentFragment extends Fragment {

    private static final String TAG = "SessionPaymentFragment";
    private static final String ARG_SESSION_ID = "sessionID";

    // session instance
    private Session mSession;

    // UI controls
    Button mPayBtn;
    Spinner mPaymentMethodSpinner;

    //TODO signature field

    // onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate called");

        // get the argument
        Bundle args = getArguments();
        if(args != null && args.containsKey(ARG_SESSION_ID)) {
            UUID sessionID = (UUID)args.getSerializable(ARG_SESSION_ID);
            mSession = SessionList.getSessionList(getActivity()).getSession(sessionID);
        }

    }

    // create the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // inflate the session payment view
        View v = inflater.inflate(R.layout.fragment_session_payment,container,false);

        // get references to the UI components
        mPayBtn = (Button)v.findViewById(R.id.payment_pay_button);
        mPaymentMethodSpinner = (Spinner)v.findViewById(R.id.session_payment_date_spinner);


        //setup the listeners
        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo complete payment
            }
        });




        return v;
    }

    //newInstance - returns new payment screen
    public static SessionPaymentFragment newInstance(UUID sessionID) {

        SessionPaymentFragment fragment = new SessionPaymentFragment();
        Bundle args = new Bundle();

        args.putSerializable(ARG_SESSION_ID,sessionID);
        fragment.setArguments(args);

        return fragment;
    }

}
