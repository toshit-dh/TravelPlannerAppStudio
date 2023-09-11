package com.example.travelplanner.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelplanner.R;

public class IntroFragment extends Fragment {

    private static final String ARG_SLIDE_POSITION = "slide_position";

    public static IntroFragment newInstance(int position) {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SLIDE_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        {
            int slidePosition = getArguments().getInt(ARG_SLIDE_POSITION);
            int layoutResId;

            // Determine which layout to inflate based on the slide position
            switch (slidePosition) {
                case 0:
                    layoutResId = R.layout.fragment_1;
                    break;
                case 1:
                    layoutResId = R.layout.fragment_2;
                    break;
                case 2:
                    layoutResId = R.layout.fragment_3;
                    break;
                default:
                    layoutResId = R.layout.fragment_default; // A default layout in case of unknown position
            }

            // Inflate the selected layout
            View rootView = inflater.inflate(layoutResId, container, false);

            return rootView;
        }
    }


}
