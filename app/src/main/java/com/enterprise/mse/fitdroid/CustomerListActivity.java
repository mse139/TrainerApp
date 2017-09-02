package com.enterprise.mse.fitdroid;

import android.support.v4.app.Fragment;

/**
 * Created by mike on 8/29/17.
 */

public class CustomerListActivity extends SingleFragmentActivity {
@Override
    protected Fragment createFragment() {
    return new CustomerListFragment();
}

}
