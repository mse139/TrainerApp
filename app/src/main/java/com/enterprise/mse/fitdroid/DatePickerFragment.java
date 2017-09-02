package com.enterprise.mse.fitdroid;

import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Date;

/**
 * Created by mike on 9/2/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int year;
    private int month;
    private int day;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        // Create a new instance of TimePickerDialog and return it

        return new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year,month,day);

    }

    public void onDateSet(DatePicker view, int yr, int month, int day) {
        // Do something with the date chosen by the user
        year = yr;
        this.month = month;
        this.day = day;
    }

    //TODO send back date
}
