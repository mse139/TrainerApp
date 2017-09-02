package com.enterprise.mse.fitdroid;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by mike on 8/31/17.
 */

public class ReceiptActivity extends SingleFragmentActivity {

    private static final String EXTRA_SESSION_ID = "sessionID";


    @Override
    protected Fragment createFragment() {
        UUID sessionID = (UUID) getIntent()
                .getSerializableExtra(EXTRA_SESSION_ID);

        // return a new receipt for the session
        return ReceiptFragment.newInstance(sessionID);
    }

    public static Intent newIntent(Context packageContext, UUID sessionID) {
        Intent intent = new Intent(packageContext, ReceiptActivity.class);
        intent.putExtra(EXTRA_SESSION_ID,sessionID);
        return intent;
    }

}
