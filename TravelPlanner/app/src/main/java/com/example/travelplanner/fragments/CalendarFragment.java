package com.example.travelplanner.fragments;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.travelplanner.R;


public class CalendarFragment extends Fragment {
    private CalendarView calendarView;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar,container,false);
        calendarView = view.findViewById(R.id.calendarview);
        textView = view.findViewById(R.id.calendartext);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int YEAR, int MONTH, int DAYOFMONTH) {
                String selectedDate = String.format("%d-%02d-%02d", YEAR, MONTH + 1, DAYOFMONTH);
                textView.setVisibility(View.VISIBLE);
                textView.setText(getString(R.string.calendattext)+selectedDate+" ? ");

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}