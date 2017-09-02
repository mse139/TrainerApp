package com.enterprise.mse.fitdroid;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

/**
 * Created by mike on 9/2/17.
 */

public class TimePickerFragment extends DialogFragment  implements TimePickerDialog.OnTimeSetListener{

    private int hour;
    private int min;
    private boolean am;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it

        return new TimePickerDialog(getActivity(), R.style.DialogTheme, this, hour, minute,
                false);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        this.hour = hourOfDay;
        this.min = minute;
    }

   //TODO send back time
}
