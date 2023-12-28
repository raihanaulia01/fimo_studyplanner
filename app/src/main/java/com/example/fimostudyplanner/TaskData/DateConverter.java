package com.example.fimostudyplanner.TaskData;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static final String DEFAULT_FORMAT = "dd MMM yyyy";
    public static String convertFromEpoch(long epochTime, String dateFormatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        Date date = new Date(epochTime);
        return dateFormat.format(date);
    }

    public static long convertToEpoch(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        try {
            Date date = dateFormat.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            Log.e("Date Converter", "convertToEpoch: Failed parsing");
            return 0;
        }
    }
    public static String convertFromEpochtoDDMMYY(long epochTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date date = new Date(epochTime);
        return dateFormat.format(date);
    }
}
