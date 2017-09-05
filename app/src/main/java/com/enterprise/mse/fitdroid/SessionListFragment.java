package com.enterprise.mse.fitdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 8/29/17.
 * this is the adapter  and viewholder for  the session list
 */

public class SessionListFragment extends Fragment {

    private RecyclerView mSessionRecyclerView;
    private SessionAdapter mAdapter;
    private static final String TAG = "SessionListFragment";
    private static final String ARG_LIST_MODE = "listMode";
    private static final String ARG_CUSTOMER_ID = "customerID";
    private int lastClickedRow = -1;
    private UUID mCustomerID;
    private FloatingActionButton mSessionAddBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate the session list fragment ui
        View view = inflater.inflate(R.layout.fragment_session_list,container,false);

        // get the arguments, if any
        Bundle args = getArguments();
        if(args != null && args.containsKey(ARG_CUSTOMER_ID)){
            mCustomerID = (UUID) args.getSerializable(ARG_CUSTOMER_ID);
            Log.d(TAG,"getting customer ID " + mCustomerID);
        }

        // get a reference to the action button
        mSessionAddBtn = view.findViewById(R.id.session_add_btn);
        // add the listener to the button to open a new session screen
        mSessionAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"FAB clicked");
                // create a new session activity
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment session = SessionFragment.newInstance(null);
                ft.addToBackStack(null);
                ft.replace(R.id.main_fragment_container,session);
                ft.commit();

            }
        });

        // add the view to the recycler view and set teh layout
        mSessionRecyclerView = (RecyclerView) view.findViewById(R.id.session_recycler_view);
        mSessionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        Log.d("onCreateView","called");
        return view;
    }



    // updates the holder with information from the session
    private void updateUI() {
        // get the session list  instance
        SessionList sessionsList = SessionList.getSessionList(getActivity());
        // get the list of sessions
        //TODO - list mode by user or by all
        List<Session> sessions ;

        // if customerID is not null, then we are in customer mode
        if(mCustomerID != null) {
            // filter the list
            Log.d(TAG,"getting the filtered list");
            sessions = sessionsList.getSessionsByCustomer(mCustomerID);

        } else
            // get full list
                sessions = sessionsList.getSessions();

        Log.d(TAG,"sessions size is " + sessions.size());

        // get the list adapter
        if(mAdapter == null ) {
            mAdapter = new SessionAdapter(sessions);
            // assign the adapter to the recycler view
            mSessionRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyItemChanged(lastClickedRow);
            lastClickedRow = -1;
            mSessionRecyclerView.setAdapter(mAdapter);
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume called");
        updateUI();
    }

    // SessionHolder holds an individual session item
    private class SessionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // UI elements of the session list item

        private TextView mSessionDate;
        private TextView mCustomerName;
        private TextView mSessionStatus;
        private TextView mSessionPaid;
        // actual session item
        private Session mSession;

        public SessionHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_session,parent,false));
            Log.d(TAG, "SessionHolder: called");
            itemView.setOnClickListener(this);
            // get references to the UI elements for this item
            mSessionDate = (TextView) itemView.findViewById(R.id.session_date);
            mCustomerName = (TextView) itemView.findViewById(R.id.customer_name);
            mSessionStatus = (TextView) itemView.findViewById(R.id.session_status);
            mSessionPaid = (TextView) itemView.findViewById(R.id.session_paid);



        }

        // add listener for tapping row

        //  click listener when user touches a row
        @Override
        public void onClick(View view) {
            Log.d(TAG,"row clicked for " + mSession.getSessionID());
          //  Intent intent = SessionActivity.newIntent(getActivity(),mSession.getSessionID());
            // store the row clicked
            lastClickedRow = getAdapterPosition();

            // start the activity and expect a result
           // startActivity(intent);

            if (getActivity().findViewById(R.id.main_fragment_container) != null ) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment initial =  SessionFragment.newInstance(mSession.getSessionID());
                ft.addToBackStack(null);
                ft.replace(R.id.main_fragment_container,initial);
                ft.commit();
            }

        }




        public void bind(Session session) {
            mSession = session;

            mSessionDate.setText(mSession.getSessionDateTime().toString());
            //TODO - get customer name from customer list once complete
            mCustomerName.setText(mSession.getCustomerID().toString());

            //if status is complete, text will say complete and be green
            if(!mSession.isComplete()) {

                mSessionStatus.setText(getResources().getString(R.string.session_incomplete));
                mSessionStatus.setTextColor(ContextCompat.getColor(getContext(),R.color.colorRed));
            }
            if(!mSession.isPaid()){
                mSessionPaid.setTextColor(ContextCompat.getColor(getContext(),R.color.colorRed));
                mSessionPaid.setText(getResources().getString(R.string.session_unpaid));

            }

        }
    }

    // session list adapter
    private class SessionAdapter extends RecyclerView.Adapter<SessionHolder> {

        // list of all the sessions
        private List<Session> mSessions;

        // constructor
        public SessionAdapter(List<Session> sessions) {
            // set reference to session list
            mSessions = sessions;

        }

        // create and return a new holder each time a new view is needed
        @Override
        public SessionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           LayoutInflater inflater = LayoutInflater.from(getActivity());
            Log.d(TAG,"onCreateViewHolder: called");
            return new SessionHolder(inflater,parent);


        }

        @Override
        public void onBindViewHolder(SessionHolder holder, int position) {
            Log.d(TAG,"onBindViewHolder called: " + Integer.toString(position));
            Session session = mSessions.get(position);
            holder.bind(session);
        }

        @Override
        public int getItemCount() {
            return mSessions.size();
        }
    }

    // new instance of the session list
    public static SessionListFragment newInstance(UUID customerID) {

        // create the new list - all records
        SessionListFragment fragment = new SessionListFragment();
        if (customerID != null) {
            Log.d(TAG,"setting session customer ID to " + customerID);
            // put the args in the bundle
            Bundle args = new Bundle();
            args.putSerializable(ARG_CUSTOMER_ID,customerID);
            fragment.setArguments(args);
        }

        return fragment;

    }
}
