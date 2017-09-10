package com.enterprise.mse.fitdroid;

import android.support.v4.app.Fragment;

/**
 * Created by mike on 9/5/17.
 */

public class SessionPaymentActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SessionPaymentFragment();
    }
}
