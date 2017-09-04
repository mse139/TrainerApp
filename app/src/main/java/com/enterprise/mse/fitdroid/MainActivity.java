package com.enterprise.mse.fitdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by mikeellis on 8/27/17.
 */

public class MainActivity extends FragmentActivity {

    private TextView mLoggedInUser; // logged in user
    private static final String LOGGED_IN_USER_KEY = "com.enterprise.mse.fitdroid.loggedinuser";
    private static TabLayout sTabLayout;
    private static ViewPager  sViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoggedInUser = (TextView) findViewById(R.id.logged_in_user_text);
        if(savedInstanceState != null) {
            // get the logged in user
            mLoggedInUser.setText( savedInstanceState.getString(LOGGED_IN_USER_KEY));
        } else
            mLoggedInUser.setText("Please Log In");

        // temporarily set the main view to session list
        if (findViewById(R.id.main_fragment_container) != null ) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment initial = new CustomerListFragment();
            ft.addToBackStack("CustomerList");
            ft.replace(R.id.main_fragment_container,initial);
            ft.commit();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LOGGED_IN_USER_KEY,mLoggedInUser.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();

        mLoggedInUser.setText(getIntent().getStringExtra(LOGGED_IN_USER_KEY));
    }


}
