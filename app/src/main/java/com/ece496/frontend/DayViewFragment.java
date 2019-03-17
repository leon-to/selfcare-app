package com.ece496.frontend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.DayView;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;

public class DayViewFragment extends Fragment {
    CalendarDayView v_day;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dayview, container, false);

        v_day = view.findViewById(R.id.v_day);
//        DayView v_dd = view.findViewById(R.id.dayvi)
//        v_day.;

        return view;
    }
}
