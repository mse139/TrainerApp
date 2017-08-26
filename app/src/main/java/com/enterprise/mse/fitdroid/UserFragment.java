package com.enterprise.mse.fitdroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mikeellis on 8/26/17.
 */

public class UserFragment extends Fragment {

    private static final String TAG="UserFragment";


    // ui controls
    private TextView mLoggedInUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstance) {

        View v = inflater.inflate(R.layout.fragment_user,container,false);

        return v;

    }
}
