package com.example.travelplanner.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.travelplanner.R;
import com.example.travelplanner.fragments.AboutFragment;

import com.example.travelplanner.fragments.DestinationFragment;
import com.example.travelplanner.fragments.FavouritesFragment;
import com.example.travelplanner.fragments.GuideFragment;
import com.example.travelplanner.fragments.HomeFragment;
import com.example.travelplanner.fragments.PlanFragment;
import com.example.travelplanner.fragments.ShareFragment;
import com.example.travelplanner.fragments.OfferFragment;
import com.example.travelplanner.fragments.ProfileFragment;
import com.example.travelplanner.fragments.AccessibilityFragment;
import com.example.travelplanner.fragments.TripFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//    ActivityMainBinding binding;
    DrawerLayout  drawerLayout;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    FloatingActionButton fab;
    BottomNavigationView bottomNavigationView;
    //private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main);

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid = item.getItemId();
                if (itemid == R.id.home) {
                    openfragment(new HomeFragment());
                    return true;
                } else if (itemid == R.id.trip) {
                    openfragment(new TripFragment());
                    return true;
                } else if (itemid == R.id.offers) {
                    openfragment(new OfferFragment());
                    return true;
                } else if (itemid == R.id.calendar) {
                    openfragment(new FavouritesFragment());
                    return true;
                }
                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        openfragment(new HomeFragment());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
    }
    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout videoLayout = dialog.findViewById(R.id.layoutVideo);
        LinearLayout liveLayout = dialog.findViewById(R.id.layoutLive);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        videoLayout.setOnClickListener(v -> {

            dialog.dismiss();
            openfragment(new PlanFragment());

        });



        liveLayout.setOnClickListener(v -> {

            dialog.dismiss();

            openfragment(new GuideFragment());

        });

        cancelButton.setOnClickListener(view -> dialog.dismiss());

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemid = item.getItemId();
        if (itemid == R.id.nav_home) {
            openfragment(new AccessibilityFragment());
        } else if (itemid == R.id.nav_settings) {
            openfragment(new com.example.travelplanner.fragments.SettingsFragment());
        } else if (itemid == R.id.nav_profile) {
           openfragment(new ProfileFragment());
        } else if (itemid == R.id.nav_about) {
            openfragment(new AboutFragment());
        } else if (itemid == R.id.nav_logout) {
            openfragment(new ShareFragment());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void openfragment (Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }
}