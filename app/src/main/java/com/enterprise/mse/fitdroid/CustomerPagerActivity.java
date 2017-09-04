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

public class CustomerPagerActivity extends AppCompatActivity {

    private static final String TAG = "CustomerPagerActivity";
    private CustomerSectionsPagerAdapter mCustomerSectionsPagerAdapter;

    private ViewPager mViewPager;

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

    private void setupViewPager(ViewPager viewPager) {

        // setup the adapters
        CustomerSectionsPagerAdapter adapter = new CustomerSectionsPagerAdapter(getSupportFragmentManager());
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

    public Intent newIntent(Context context, UUID customerID) {

    }

}
