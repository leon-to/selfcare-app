package com.ece496.selfcare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.framgia.library.calendardayview.CalendarDayView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MonthViewFragment extends Fragment {
    private CalendarView v_month;
    private CalendarDayView v_day;
    private Toolbar v_toolbar;

    private DayViewFragment f_dayview;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_monthview, container, false);

        v_month = view.findViewById(R.id.calendarView);
        v_toolbar = getActivity().findViewById(R.id.toolbar);

        f_dayview = new DayViewFragment();

        v_month.setOnDayClickListener(eventDay ->
            {
                // set toolbar title
                Date date = eventDay.getCalendar().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");

                v_toolbar.setTitle(formatter.format(date));
                // set frame content
                getFragmentManager().beginTransaction()
                        .replace(R.id.flContent, f_dayview)
                        .commit();
            }
        );

        return view;
    }
}
