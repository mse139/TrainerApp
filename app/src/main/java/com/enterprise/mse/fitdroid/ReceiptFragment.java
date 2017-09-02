package com.enterprise.mse.fitdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by mike on 8/31/17.
 * This is the receipt controller
 */

public class ReceiptFragment extends Fragment {

    // session instance
    private Session mSession;
    private static final String TAG = "ReceiptFragment";
    private static final String ARG_SESSION_ID = "sessionID";


    // ui controls
    private TextView mCustomerName;
    private TextView mSessionDate;
    private TextView mAmountReceived;
    private TextView mReceivedOn;
    private TextView mPaymentMethod;

    //TODO signature file


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the session id argument to get the payment info
        UUID sessionID = (UUID)getArguments().getSerializable(ARG_SESSION_ID);

        mSession = SessionList.getSessionList(getActivity()).getSession(sessionID);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle onSavedInstance) {
        // inflate the view
        View v = inflater.inflate(R.layout.fragment_receipt,container,false);

        // get ui references
        mCustomerName = (TextView) v.findViewById(R.id.receipt_customer_name);
        mSessionDate = (TextView) v.findViewById(R.id.receipt_session_date);
        mAmountReceived = (TextView)v.findViewById(R.id.receipt_amount_received);
        mReceivedOn = (TextView) v.findViewById(R.id.receipt_received_date);
        mPaymentMethod = (TextView) v.findViewById(R.id.receipt_payment_method);

        return v;
    }

    // newInstance - returns a new Receipt with the sessionID
    public static ReceiptFragment newInstance(UUID sessionID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SESSION_ID, sessionID);
        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
