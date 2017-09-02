package com.enterprise.mse.fitdroid;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by mike on 8/28/17.
 */

public class BillingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        Log.d("BillingActivity","createFragment");
        return new BillingFragment();
    }

}


