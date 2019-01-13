package com.ece496.selfcare;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                List<EventDay> events = new ArrayList<>();

                Calendar calendar = Calendar.getInstance();
                events.add(new EventDay(clickedDayCalendar, R.drawable.sample_circle));

                try {
                    calendarView.setDate(clickedDayCalendar);
                    calendarView.setEvents(events);
                } catch (OutOfDateRangeException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
