package com.example.travelplanner.utils;

import com.example.travelplanner.data.Destination;

import java.util.ArrayList;
import java.util.List;

public class FavoriteManager {
    private static List<Destination> favoriteDestinations = new ArrayList<>();

    public static List<Destination> getFavoriteDestinations() {
        return favoriteDestinations;
    }

    public static void addFavoriteDestination(Destination destination) {
        favoriteDestinations.add(destination);
    }

    public static void removeFavoriteDestination(Destination destination) {
        favoriteDestinations.remove(destination);
    }

    public static boolean isFavorite(Destination destination) {
        return favoriteDestinations.contains(destination);
    }
}

