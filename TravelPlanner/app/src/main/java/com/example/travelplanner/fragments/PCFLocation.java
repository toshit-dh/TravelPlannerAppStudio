package com.example.travelplanner.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelplanner.R;
import com.example.travelplanner.utils.OnFieldsChangeListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PCFLocation extends Fragment{
    private AutoCompleteTextView countryAutoCompleteTextView;
    private AutoCompleteTextView cityAutoCompleteTextView;
    private AutoCompleteTextView countryDesAutoCompleteTextView;
    private AutoCompleteTextView cityDesAutoCompleteTextView;
    private TextView destext;
    private OnFieldsChangeListener listner;

    private List<String> citySuggestions = new ArrayList<>();
    private JSONObject countryCityMap;
    private List<String> countrySuggestions = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_p_c_f_location, container, false);
        countryAutoCompleteTextView = view.findViewById(R.id.planedittextcountry);
        cityAutoCompleteTextView = view.findViewById(R.id.planedittextcity);
        countryDesAutoCompleteTextView = view.findViewById(R.id.planedittextcountrydes);
        cityDesAutoCompleteTextView = view.findViewById(R.id.planedittextcitydes);
        destext = view.findViewById(R.id.destext);
        setupAutoCompleteTextView();
        return view;
    }

    private void setupAutoCompleteTextView() {
        int resourceId = getResources().getIdentifier("countriesndcities", "raw", getActivity().getPackageName());
        if(resourceId!=0){
        InputStream is = getResources().openRawResource(resourceId);
        try {
            // Read the JSON file from the assets folder
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonString = new String(buffer, "UTF-8");

            // Now you can parse the jsonString as JSON
            JSONObject jsonObject = new JSONObject(jsonString);
            countryCityMap = new JSONObject(jsonString);
            // Iterate through the JSON object and extract country names
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String country = keys.next();
                Log.d("CountryName", country);
                Log.d("Cities", jsonObject.getJSONArray(country).toString());
                countrySuggestions.add(country);
            }

            // Create an ArrayAdapter for the country suggestions
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, countrySuggestions);
            // Set the adapter for the AutoCompleteTextView
            countryAutoCompleteTextView.setAdapter(adapter);
            // Handle suggestion selection
            countryAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedCountry = (String) parent.getItemAtPosition(position);
//                    // Handle the selected country, e.g., display a toast
//                    Toast.makeText(getActivity(), "Selected Country: " + selectedCountry, Toast.LENGTH_SHORT).show();
                    try {
                        JSONArray citiesArray = countryCityMap.getJSONArray(selectedCountry);
                        citySuggestions.clear();
                        for (int i = 0; i < citiesArray.length(); i++) {
                            citySuggestions.add(citiesArray.getString(i));
                        }

                        // Set up the AutoCompleteTextView for cities based on the selected country
                        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, citySuggestions);
                        cityAutoCompleteTextView.setAdapter(cityAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Hide the keyboard after selection
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(countryAutoCompleteTextView.getWindowToken(), 0);
                }
            });
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }else {
            Toast.makeText(getActivity(), "error" , Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<String> countryDesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, countrySuggestions);
        countryDesAutoCompleteTextView.setAdapter(countryDesAdapter);
        countryDesAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountryDes = (String) parent.getItemAtPosition(position);
                // Handle the selected destination country
                //Toast.makeText(getActivity(), "Selected Destination Country: " + selectedCountryDes, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray citiesArrayDes = countryCityMap.getJSONArray(selectedCountryDes);
                    citySuggestions.clear();
                    for (int i = 0; i < citiesArrayDes.length(); i++) {
                        citySuggestions.add(citiesArrayDes.getString(i));
                    }

                    // Set up the AutoCompleteTextView for destination cities
                    ArrayAdapter<String> cityDesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, citySuggestions);
                    cityDesAutoCompleteTextView.setAdapter(cityDesAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Hide the keyboard after selection
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(countryDesAutoCompleteTextView.getWindowToken(), 0);
            }
        });

        // Handle suggestion selection for destination city
        cityDesAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCityDes = (String) parent.getItemAtPosition(position);
                String sourceCity = cityAutoCompleteTextView.getText().toString().trim();
                String destinationCity = cityDesAutoCompleteTextView.getText().toString().trim();
                if (sourceCity.equals(destinationCity)){
                    cityDesAutoCompleteTextView.setError("Destination cannot be same as source");
                }

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(cityDesAutoCompleteTextView.getWindowToken(), 0);

            }
        });
}
    public boolean isSelected() {
        // Get the selected source and destination cities
        String destinationCountry = countryDesAutoCompleteTextView.getText().toString().trim();
        String sourceCountry = countryAutoCompleteTextView.getText().toString().trim();
        String sourceCity = cityAutoCompleteTextView.getText().toString().trim();
        String destinationCity = cityDesAutoCompleteTextView.getText().toString().trim();

        // Check if all fields are filled and source and destination cities are not the same
        if (!sourceCity.isEmpty() && !destinationCity.isEmpty() && !destinationCountry.isEmpty() && !sourceCountry.isEmpty()) {
            return true; // All conditions are met, return true
        } else {
            cityDesAutoCompleteTextView.setError("Fill Out All Fields");
            return false; // Conditions are not met, return false
        }
    }
    public void onFieldsChanged(boolean filled) {
        Log.d("PCFLocation", "onFieldsChanged called with filled: " + filled);
        Toast.makeText(getActivity(), "onfc", Toast.LENGTH_SHORT).show();
        String destinationCountry = countryDesAutoCompleteTextView.getText().toString().trim();
        String sourceCountry = countryAutoCompleteTextView.getText().toString().trim();
        String sourceCity = cityAutoCompleteTextView.getText().toString().trim();
        String destinationCity = cityDesAutoCompleteTextView.getText().toString().trim();
        if (!sourceCity.isEmpty() && !destinationCity.isEmpty() && !destinationCountry.isEmpty() && !sourceCountry.isEmpty()) {
            if (listner!=null){
                listner.onFieldsChanged(filled);
        }
        }

    }
    public void setOnFieldsChangeListener(OnFieldsChangeListener listener) {
        this.listner = listener;
    }

}


