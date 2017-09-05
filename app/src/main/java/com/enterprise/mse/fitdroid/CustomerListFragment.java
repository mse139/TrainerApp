package com.enterprise.mse.fitdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mike on 8/29/17.
 * adapter/viewholder for the customer list
 */

public class CustomerListFragment extends Fragment {

    private RecyclerView mCustomerRecyclerView;
    private CustomerAdapter mAdapter;
    private static final String TAG = "CustomerListFragment";
    private final static String ARG_CUSTOMER = "customerID";
    private int lastClickedRow = -1;
    private FloatingActionButton mCustomerAddBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflate the customer list fragment
        View view = inflater.inflate(R.layout.fragment_customer_list,container,false);

        mCustomerRecyclerView = (RecyclerView) view.findViewById(R.id.customer_recycler_view);
        mCustomerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // get the reference to the add button
        mCustomerAddBtn = (FloatingActionButton)view.findViewById(R.id.customer_add_btn);
        // add the listener
        mCustomerAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"customer add btn clicked");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = CustomerFragment.newInstance(null);
                ft.addToBackStack(null);
                ft.replace(R.id.main_fragment_container,fragment);
                ft.commit();
            }
        });

        updateUI();
        return view;
    }

    private void updateUI() {
        // get list instance
        CustomerList customerList = CustomerList.getCustomerList(getActivity());
        List<Customer> customers = customerList.getCustomers();

        mAdapter = new CustomerAdapter(customers);


        mCustomerRecyclerView.setAdapter(mAdapter);

        if(lastClickedRow >=0){
            mAdapter.notifyItemChanged(lastClickedRow);
            lastClickedRow = -1;
        }



    }

    //@CustomerHolder:  holds an individual customer line
    private class CustomerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // UI elements for each customer item

        private TextView mCustomerName;
        private ImageView mCustomerAvatar;
        private Customer mCustomer;

        //ctor
        public CustomerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_customer, parent, false));

            // get references to UI components
            mCustomerName = (TextView) itemView.findViewById(R.id.customer_list_name);
            mCustomerAvatar = (ImageView) itemView.findViewById(R.id.customer_list_avatar);
            itemView.setOnClickListener(this);

        }

        public void bind(Customer customer) {
            mCustomer = customer;

            mCustomerName.setText(mCustomer.getCustomerName());
            //todo: set avatar


        }

        // clicking of row will activate that customer's records
        @Override
        public void onClick(View view) {
            lastClickedRow = getAdapterPosition();
            Log.d(TAG,"record clicked " + mCustomer.getCustomerID());

            // create the pager intent

            if (getActivity().findViewById(R.id.main_fragment_container) != null ) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment initial =  CustomerPagerActivity.newInstance(mCustomer.getCustomerID());
                ft.addToBackStack("Customer List");
                ft.replace(R.id.main_fragment_container,initial);
                ft.commit();
            }



        }
    }
        //@CustomerAdapeter:  customer list adapter
        private class CustomerAdapter extends RecyclerView.Adapter<CustomerHolder> {

            private List<Customer> mCustomers;

            //ctor
            public CustomerAdapter(List<Customer> customers) {
                // set reference to list
                mCustomers = customers;
            }

            @Override
            public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                return new CustomerHolder(inflater,parent);
            }

            @Override
            public void onBindViewHolder(CustomerHolder holder, int position) {
                Customer customer = mCustomers.get(position);
                holder.bind(customer);
            }

            @Override
            public int getItemCount() {
                return mCustomers.size();
            }


        }

    }

