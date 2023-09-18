package com.example.travelplanner.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.travelplanner.R;

import java.util.Calendar;


public class PCFDate extends Fragment {
    static DatePicker datePicker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_p_c_f_date,container,false);
        datePicker = view.findViewById(R.id.datepicker);
        return view;
    }
    public boolean isSelected(){
        return true;
    }
    // Get the current date
    Calendar calendar = Calendar.getInstance();
    int initialYear = calendar.get(Calendar.YEAR);
    int initialMonth = calendar.get(Calendar.MONTH);
    int initialDay = calendar.get(Calendar.DAY_OF_MONTH);
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date here
                        isSelected();

                        // Do something with the selectedDate
                    }
                },
                // Set initial year, month, and day if needed
                initialYear,
                initialMonth,
                initialDay
        );

        datePickerDialog.show();
    }

}