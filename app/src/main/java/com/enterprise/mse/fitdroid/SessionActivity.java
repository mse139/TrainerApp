package com.enterprise.mse.fitdroid;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class SessionActivity extends SingleFragmentActivity {

    private static final String EXTRA_SESSION_ID = "sessionID";


    @Override
    protected Fragment createFragment() {
        UUID sessionID = (UUID) getIntent()
                .getSerializableExtra(EXTRA_SESSION_ID);

        // return a new receipt for the session
        return SessionFragment.newInstance(sessionID);
    }

    public static Intent newIntent(Context packageContext, UUID sessionID) {
        Intent intent = new Intent(packageContext, SessionActivity.class);

        if(sessionID != null) {
            intent.putExtra(EXTRA_SESSION_ID,sessionID);
        }

        return intent;
    }



}
