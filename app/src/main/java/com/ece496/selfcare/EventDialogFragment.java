package com.ece496.selfcare;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.NumberPicker;

import com.applandeo.materialcalendarview.EventDay;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

public class EventDialogFragment extends DialogFragment {
    private NumberPicker np_hour;

    private Button b_add_event;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.dialog_event, container, false);

        // build hour picker
        np_hour = view.findViewById(R.id.np_hour);
        String[] HOUR = new String[]{
            "12AM", "1AM", "2AM", "3AM", "4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM",
            "12PM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM"
        };
        np_hour.setMinValue(0);
        np_hour.setMaxValue(HOUR.length-1);
        np_hour.setDisplayedValues(HOUR);
        np_hour.setValue(LocalDateTime.now().getHour());


        // set onClick for button "add event"
        b_add_event = view.findViewById(R.id.b_add_event);
        b_add_event.setOnClickListener(v ->
                System.out.println(np_hour.getValue())
        );



        return view;
    }

    private void add_event(){
//        EventDay event = new EventDay();

        //        np_year.getV;
//
//        List<EventDay> events = new ArrayList<>();
//
//        Calendar calendar = Calendar.getInstance();
//        events.add(new EventDay(calendar, R.drawable.sample_icon));
////or
//        events.add(new EventDay(calendar, new Drawable()));
//
//        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
//        calendarView.setEvents(events);
    }
}
