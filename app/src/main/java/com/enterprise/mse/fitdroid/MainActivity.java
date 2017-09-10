package com.enterprise.mse.fitdroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


/**
 * Created by mikeellis on 8/27/17.
 */

public class MainActivity extends AppCompatActivity {

    private TextView mLoggedInUser; // logged in user
    private final static String TAG = "MainActivity";
    private static final String LOGGED_IN_USER_KEY = "com.enterprise.mse.fitdroid.loggedinuser";
    private static TabLayout sTabLayout;
    private static ViewPager  sViewPager;
    // navigation drawer
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private String[] navMenuValues;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActionBar mActionBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup the navigation drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        navMenuValues = (String[]) getResources().getStringArray(R.array.nav_draw_list);
        // set the adapter for the drawer

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,navMenuValues));

        // set the drawer icon
         mActionBar = getSupportActionBar();
        if(mActionBar!=null)
        {
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }


        // setup the menu click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Class fragClass = null;

                // assign the fragment class based on which item the user touches
                switch(i) {
                    case 0:
                        fragClass = CustomerListFragment.class;
                        startFragment(fragClass);
                        break;
                    case 1:
                        fragClass = SessionListFragment.class;
                        startFragment(fragClass);
                        break;
                    case 2:
                        //logout and restart login screen
                        Toast.makeText(getApplicationContext(),getString(R.string.logout_message),Toast.LENGTH_SHORT).show();
                        startLogin();
                        break;
                }

                // close the drawer
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.nav_drawer);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        // setup the drawer open/close listeners
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.action_drawer_open, R.string.action_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }


        };


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        // setup the logged in user and start the first activity

        mLoggedInUser = (TextView) findViewById(R.id.logged_in_user_text);
        if(savedInstanceState != null) {
            // get the logged in user
            mLoggedInUser.setText( savedInstanceState.getString(LOGGED_IN_USER_KEY));
        } else
            mLoggedInUser.setText("Please Log In");

        //  set the main view to session list
        if (findViewById(R.id.main_fragment_container) != null ) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment initial =  SessionListFragment.newInstance(null);
            ft.addToBackStack("SessionList");
            ft.replace(R.id.main_fragment_container,initial);
            ft.commit();
        }


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }



    // start fragment
    private void startFragment(Class fragClass) {

        Fragment fragment = null;

        // do not start login activity
        if (fragClass == LoginActivity.class)
            return;

        try{
            fragment = (Fragment)fragClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(fragment,null);
        ft.replace(R.id.main_fragment_container,fragment);
        ft.commit();
    }

    private void startLogin() {

        Intent intent = new Intent(this,LoginActivity.class);
        // clears the backstack from prior activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


}
