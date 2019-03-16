package com.ece496.selfcare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class EventDialogFragment extends DialogFragment {

    private NumberPicker np_year;
    private NumberPicker np_month;
    private NumberPicker np_day;
    private NumberPicker np_hour;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.dialog_event, container, false);

        np_year = view.findViewById(R.id.np_year);
        np_month = view.findViewById(R.id.np_month);
        np_day = view.findViewById(R.id.np_day);
        np_hour = view.findViewById(R.id.np_hour);

        // CONSTANT VARS
        LocalDateTime now = LocalDateTime.now();
        int n_year = 2;
        String[] YEAR = new String[n_year];
        for(int i=0; i<2; i++){
            YEAR[i] = String.valueOf(now.getYear() + i);
        }

        String[] MONTH = new String[]{
            "JAN", "FEB", "MAR", "APR", "MAY", "JUN",
            "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"
        };

        String[] DAY = new String[]{
            "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"
        };

        String[] HOUR = new String[]{
            "12AM", "1AM", "2AM", "3AM", "4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM",
            "12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM"
        };


        NumberPicker[] np_list = new NumberPicker[]{np_year, np_month, np_day, np_hour};
        String[][] s_list = new String[][]{YEAR, MONTH, DAY, HOUR};

        for(int i=0; i<np_list.length; i++){
            np_list[i].setMinValue(0);
            np_list[i].setMaxValue(s_list[i].length-1);
            np_list[i].setDisplayedValues(s_list[i]);
        }





        return view;
    }

}
