package com.enterprise.mse.fitdroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mike on 9/2/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePicker mDatePicker;
    private int year;
    private int month;
    private int day;

    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.enterprise.mse.fitdroid.date";



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // get the view

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker,null);
        mDatePicker = (DatePicker) view.findViewById(R.id.date_picker);

        // get the arg sent
        Date date=null;
        if(getArguments().containsKey(ARG_DATE)) {
            date =  (Date) getArguments().getSerializable(ARG_DATE);
        }
        if(date == null) // fill with current date if not sent
            date = new Date();



        // Use the current time as the default values for the picker
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        // Create a new instance of DatePicker and return it

        return new AlertDialog.Builder(getActivity()).setView(view).
                setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    // construct the date and send it back to the calling activity
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year=  mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year,month,day).getTime();
                        sendResult(Activity.RESULT_OK,date);
                    }
                })
                .create();

    }

    public void onDateSet(DatePicker view, int yr, int month, int day) {
        // Do something with the date chosen by the user
        year = yr;
        this.month = month;
        this.day = day;
    }

    //TODO send back date

    //newInstance allows passing of the date parameter to the UI
    public static DatePickerFragment newInstance(Date date) {

        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);
        fragment.setArguments(args);


        return fragment;

    }

    // this sends back the date result
    private void sendResult(int resultCode,Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
