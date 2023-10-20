package com.example.travelplanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.data.Destination;
import com.example.travelplanner.data.Trips;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    private List<Trips> tripsList;
    private TripAdapter tripAdapter;
    private Context context;
    public void addDestinationToTrip(Destination destination) {
        if (tripAdapter != null) {
            tripAdapter.addDestinationToTrip(destination);
        }
    }

    public TripAdapter(Context context,List<Trips> tripsList){
        this.context = context;
        this.tripsList = tripsList;
    }
    @NonNull
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.ViewHolder holder, int position) {
        Trips trips1 = tripsList.get(position);
        holder.sourceCountry.setText(trips1.getSourceCountry());
        holder.sourceCity.setText(trips1.getSourceCity());
        holder.destinationCity.setText(trips1.getDestinationCity());
        holder.destinationCountry.setText(trips1.getDestinationCountry());
        holder.date.setText(trips1.getTripDate());
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public EditText sourceCity;
        public EditText sourceCountry;
        public EditText destinationCity;
        public EditText destinationCountry;
        public EditText date;
        public ViewHolder(View itemView) {
            super(itemView);
            sourceCity = itemView.findViewById(R.id.sourceCityEditText);
            sourceCountry = itemView.findViewById(R.id.sourceCountryEditText);
            destinationCity = itemView.findViewById(R.id.destinationCityEditText);
            destinationCountry = itemView.findViewById(R.id.destinationCountryEditText);
            date = itemView.findViewById(R.id.dateEditText);
        }
    }

}
