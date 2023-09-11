package com.example.travelplanner.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelplanner.R;
import com.example.travelplanner.activities.Home;
import com.example.travelplanner.data.MyPrefs;
import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button Logoutbtn;
    private Button Cancelbtn;
    private TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        mAuth = FirebaseAuth.getInstance();

        Logoutbtn = findViewById(R.id.btnLogout);
        Cancelbtn = findViewById(R.id.btnCancel);
        password = findViewById(R.id.passBox);

        Logoutbtn.setOnClickListener(v -> {
            if(password.getText().toString().equals(MyPrefs.getPassword(this))){
                mAuth.signOut();
                Toast.makeText(Logout.this, "Logout Successful !", Toast.LENGTH_SHORT).show();
                MyPrefs.setSignUpCompletedStatus(Logout.this,false);

                MyPrefs.deletePassword(Logout.this);
                startActivity(new Intent(Logout.this, Login.class));
            }else {
                password.setError("Incorrect Password");
            }

        });
        Cancelbtn.setOnClickListener(view1 -> {
            startActivity(new Intent(this, Home.class));
        });

    }
}