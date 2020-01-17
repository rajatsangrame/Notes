package com.example.rajat.notes.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Rajat Sangrame on 17/1/20.
 * http://github.com/rajatsangrame
 */
public class BindingUtils {

    /**
     * @param @{@link Long} current timestamp
     * @return @{@link String} date for ex Friday, 17 Jan 2020
     */
    public static String getTimeStampString(Long timestamp) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(timestamp);
        return DateFormat.format("dd MMMM yyyy, hh:mm a", cal).toString();
    }
}
