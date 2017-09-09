package com.enterprise.mse.fitdroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mike on 9/2/17.
 */

public class TimePickerFragment extends DialogFragment  {


    private static final String ARG_TIME = "time";
    public static final String EXTRA_TIME = "com.enterprise.mse.fitdroid.time";
    public static final String TAG = "TimePicker";
    private int hour;
    private int minute;

    private TimePicker mTimePicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // get the view
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_time_picker,null);
        mTimePicker = (TimePicker)view.findViewById(R.id.time_picker);
        mTimePicker.setIs24HourView(false);
        // get the time from the bundle arg
        Date date=null;
        if(getArguments().containsKey(ARG_TIME)) {
            date = (Date)getArguments().getSerializable(ARG_TIME);
        }
        if (date == null)
            date=  new Date();


        // Use the arg time as the default values for the picker
         Calendar c = Calendar.getInstance();
        c.setTime(date);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        mTimePicker.setHour(hour);
        mTimePicker.setMinute(minute);

        // Create a new instance of TimePickerDialog and return it

        return new AlertDialog.Builder(getActivity()).setView(view)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int minute = mTimePicker.getMinute();
                        int hour = mTimePicker.getHour();
                        Calendar cal =  Calendar.getInstance();
                        cal.set(Calendar.HOUR_OF_DAY ,hour);
                        cal.set(Calendar.MINUTE,minute);

                        Date date = cal.getTime();
                        Log.d(TAG,"time set is: " + date.toString());
                        sendResult(Activity.RESULT_OK,date);
                    }
                }).create();
    }


    private void sendResult(int resultCode, Date date) {
        if(getTargetFragment() == null)
            return;
        // build the return intent
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);

    }

    public static TimePickerFragment newInstance(Date date) {

        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME,date);
        fragment.setArguments(args);


        return fragment;

    }
}
