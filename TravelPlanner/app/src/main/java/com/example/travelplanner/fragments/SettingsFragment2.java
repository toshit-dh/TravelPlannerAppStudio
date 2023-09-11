package com.example.travelplanner.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.travelplanner.R;

public class SettingsFragment2 extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}