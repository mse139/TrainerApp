package com.enterprise.mse.fitdroid;

import android.support.v4.app.Fragment;

/**
 * Created by mike on 8/29/17.
 * handles the session list
 */

public class SessionListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SessionListFragment();
    }
}
