package com.example.travelplanner.fragments;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.travelplanner.R;

import java.util.Calendar;


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
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String selectedDate = String.format("%d-%02d-%02d", year, month + 1, day);
                textView.setVisibility(View.VISIBLE);
                textView.setText(getString(R.string.calendattext)+selectedDate+" ? ");

            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Replace the current fragment with SecondFragment
                PlanFragment planFragment = new PlanFragment();
                fragmentTransaction.replace(R.id.frame_layout, planFragment);

                // Add the transaction to the back stack
                fragmentTransaction.addToBackStack(null);

                // Commit the transaction
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}