package com.example.travelplanner.fragments;



import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelplanner.R;
import com.example.travelplanner.adapters.DestinationAdapter;
import com.example.travelplanner.adapters.DestinationAdapter2;
import com.example.travelplanner.adapters.TripAdapter;
import com.example.travelplanner.data.Trips;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class TripFragment extends Fragment {
    private List<Trips> tripsList = new ArrayList<>();
    private TripAdapter tripAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip,container,false);

        RecyclerView recyclerView = view.findViewById(R.id.tripsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false)); // Add this line
        tripAdapter = new TripAdapter(requireContext(),loadTripsFromJsonFile(requireContext()));
        recyclerView.setAdapter(tripAdapter);
        return view;
    }
    public List<Trips> loadTripsFromJsonFile(Context context) {
        tripsList = new ArrayList<>();

        try {
            // Define the filename for the JSON file
            String filename = "tips.json";

            // Open the file for reading
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            // Read the JSON data from the file
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                json.append(line);
            }

            // Close the streams
            isr.close();
            fis.close();

            // Use Gson to deserialize the JSON data into a list of Trips objects
            Gson gson = new Gson();
            Type tripsListType = new TypeToken<List<Trips>>() {}.getType();
            tripsList = gson.fromJson(json.toString(), tripsListType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tripsList;
    }
    }
