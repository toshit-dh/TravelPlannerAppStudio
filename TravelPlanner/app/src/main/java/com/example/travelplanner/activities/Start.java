package com.example.travelplanner.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.travelplanner.R;
import com.example.travelplanner.data.MyPrefs;
import com.example.travelplanner.ui.login.Login;

public class Start extends AppCompatActivity {
    private ImageView animatedImage;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        animatedImage = findViewById(R.id.animatedImage);

        // Animate the image
        animateImage();
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.appstartsound);
        mediaPlayer.start();

        textView = findViewById(R.id.plantext);
        textView.setSelected(true);
 //

        // Use the Looper from the main thread
        Looper mainLooper = Looper.getMainLooper();

        // Delayed runnable to finish the activity after 5 seconds
        new Handler(mainLooper).postDelayed(() -> {

            if (MyPrefs.getIntroCompletedStatus(this)) {
                if(MyPrefs.getSignUpCompletedStatus(this)){
                // User has seen the introduction screen before, navigate to the main app screen
                startActivity(new Intent(this, Home.class));
                finish();
                } else {
                    startActivity(new Intent(this,Login.class));
                    finish();
                }
            } else if (!MyPrefs.getIntroCompletedStatus(this)) {
                startActivity(new Intent(this, Explore.class));
                finish();
            }

        }, 6000); // 5000 milliseconds (5 seconds)
    }
//true true = nav
//true false = login
    private void animateImage() {
        ObjectAnimator slideInAnimator = ObjectAnimator.ofFloat(animatedImage, "translationX", -500f, 0f);
        slideInAnimator.setDuration(5000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(slideInAnimator);

        animatorSet.start();

        // Use the Looper from the main thread
        Looper mainLooper = Looper.getMainLooper();

        // Delay for 3 seconds (3000 milliseconds)
        new Handler(mainLooper).postDelayed(() -> {
            // Delayed runnable to perform actions after a pause
            // For example, you can pause the animation, display the image for 3 seconds, and then resume animation.
            animatedImage.clearAnimation(); // Clear any ongoing animations

            // Delay for another 3 seconds (3000 milliseconds)
            new Handler(mainLooper).postDelayed(() -> {
                // Animate the image out
                ObjectAnimator slideOutAnimator = ObjectAnimator.ofFloat(animatedImage, "translationX", 0f, -500f);
                slideOutAnimator.setDuration(3000);

                AnimatorSet animatorSetOut = new AnimatorSet();
                animatorSetOut.play(slideOutAnimator);

                animatorSetOut.start();
            }, 3000);
        }, 3000);
    }
}
