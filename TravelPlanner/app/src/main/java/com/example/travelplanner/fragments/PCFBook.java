package com.example.travelplanner.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.travelplanner.R;


public class PCFBook extends Fragment {
    private AutoCompleteTextView countryAutoCompleteTextView;
    private AutoCompleteTextView cityAutoCompleteTextView;
    private AutoCompleteTextView countryDesAutoCompleteTextView;
    private AutoCompleteTextView cityDesAutoCompleteTextView;
    private RadioButton route;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_p_c_f_book,container,false);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return view;
    }
}