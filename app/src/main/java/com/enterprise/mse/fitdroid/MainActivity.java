package com.enterprise.mse.fitdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by mikeellis on 8/27/17.
 */

public class MainActivity extends AppCompatActivity {

    private TextView mLoggedInUser; // logged in user
    private static final String LOGGED_IN_USER_KEY = "com.enterprise.mse.fitdroid.loggedinuser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            // get the logged in user
            mLoggedInUser.setText( savedInstanceState.getString(LOGGED_IN_USER_KEY));
        } else
            mLoggedInUser.setText(getResources().getString(R.id.logged_in_user_default));

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onCreate(outState);

        outState.putString(LOGGED_IN_USER_KEY,mLoggedInUser.getText().toString());
    }
}
