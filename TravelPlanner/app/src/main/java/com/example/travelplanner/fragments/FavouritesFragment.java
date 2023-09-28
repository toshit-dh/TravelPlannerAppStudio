package com.example.travelplanner.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.adapters.FavouriteAdapter;
import com.example.travelplanner.data.Destination;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment {
    private List<Destination> favoriteDestinations = new ArrayList<>();
    private FavouriteAdapter favoritesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.favRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoritesAdapter = new FavouriteAdapter(favoriteDestinations);
        recyclerView.setAdapter(favoritesAdapter);

        return view;
    }

    // Add a method to update the list of favorite destinations
    public void updateFavorites(List<Destination> favorites) {
        favoriteDestinations.clear();
        favoriteDestinations.addAll(favorites);
        favoritesAdapter.notifyDataSetChanged();
    }
}
