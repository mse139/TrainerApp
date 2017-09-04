package com.enterprise.mse.fitdroid;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerPagerActivity extends Fragment {

    private static final String TAG = "CustomerPagerActivity";
    private CustomerSectionsPagerAdapter mCustomerSectionsPagerAdapter;
    private static final String ARG_CUSTOMER_ID = "customerID";
    private ViewPager mViewPager;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"starting");
        setContentView(R.layout.customer_main_layout);

        mCustomerSectionsPagerAdapter = new CustomerSectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.customer_main_layout);
        setupViewPager(mViewPager);

        // setup the tabs with pager
        TabLayout tabLayout = (TabLayout)findViewById(R.id.customer_tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.customer_main_layout,container,false);

        mCustomerSectionsPagerAdapter = new CustomerSectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) getActivity().findViewById(R.id.customer_main_layout);
        setupViewPager(mViewPager);

        return v;
    }




    private void setupViewPager(ViewPager viewPager) {

        // setup the adapters
        CustomerSectionsPagerAdapter adapter = new CustomerSectionsPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new CustomerFragment(),"Information");
        adapter.addFragment(new SessionListFragment(),"Sessions");
        adapter.addFragment(new BillingFragment(),"Billing");

        // set the adapter
        viewPager.setAdapter(adapter);
    }


    public class CustomerSectionsPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public CustomerSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

    public static CustomerPagerActivity newInstance(UUID customerID) {

        CustomerPagerActivity fragment = new CustomerPagerActivity();

        // setup the bundle to accept the customer arg
        Bundle args = new Bundle();

        if (customerID != null) {
            args.putSerializable(ARG_CUSTOMER_ID,customerID);

        }
        fragment.setArguments(args);
        return fragment;
    }

}
