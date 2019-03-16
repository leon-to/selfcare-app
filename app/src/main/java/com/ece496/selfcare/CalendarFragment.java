package com.ece496.selfcare;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarFragment extends Fragment {
    private CalendarView v_calendar;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_calendar, container, false);

        v_calendar = view.findViewById(R.id.calendarView);




        v_calendar.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                List<Calendar> l_calendar = new ArrayList<>();
                l_calendar.add(eventDay.getCalendar());
                v_calendar.setSelectedDates(l_calendar);
                System.out.println("day: " + l_calendar);
            }

        });

        return view;
    }
}
