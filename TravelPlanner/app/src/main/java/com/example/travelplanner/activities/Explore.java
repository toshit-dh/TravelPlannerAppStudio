package com.example.travelplanner.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.travelplanner.R;
import com.example.travelplanner.adapters.IntroPagerAdapter;
import com.example.travelplanner.data.MyPrefs;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Explore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro); // Set the layout

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        Button skipButton = findViewById(R.id.skipButton);

        Toast.makeText(this, "Slide to view more content", Toast.LENGTH_SHORT).show();

        IntroPagerAdapter introPagerAdapter = new IntroPagerAdapter(this);
        viewPager.setAdapter(introPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("")).attach();

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // Check if the last slide is reached
                if (position == introPagerAdapter.getItemCount() - 1) {
                    skipButton.setText("Continue"); // Update button text
                } else {
                    skipButton.setText("Skip"); // Reset button text
                }
            }
        });

        skipButton.setOnClickListener(v -> {
            // Navigate to the next activity
            MyPrefs.setIntroCompletedStatus(this,true);
            startActivity(new Intent(Explore.this, com.example.travelplanner.ui.login.Signup.class));
            finish(); // Optional: Close the current activity
        });
    }

}
