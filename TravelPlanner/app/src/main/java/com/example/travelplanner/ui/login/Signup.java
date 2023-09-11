package com.example.travelplanner.ui.login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelplanner.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                Helper helperClass = new Helper(name, email, username, password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(Signup.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
//
//    }
//    EditText signupName, signupUsername, signupEmail, signupPassword;
//    TextView loginRedirectText;
//    Button signupButton;
//    FirebaseDatabase database;
//    DatabaseReference reference;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        //DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//        signupName = findViewById(R.id.signup_name);
//        signupEmail = findViewById(R.id.signup_email);
//        signupUsername = findViewById(R.id.signup_username);
//        signupPassword = findViewById(R.id.signup_password);
//        loginRedirectText = findViewById(R.id.loginRedirectText);
//        signupButton = findViewById(R.id.signup_button);
//        loginRedirectText.setOnClickListener(v -> {
//            Intent intent = new Intent(Signup.this, Login.class);
//            startActivity(intent);
//        });
//        signupButton.setOnClickListener(view -> {
//
//            database = FirebaseDatabase.getInstance();
//            reference = database.getReference("users");
//
//            String name = signupName.getText().toString();
//            String email = signupEmail.getText().toString();
//            String username = signupUsername.getText().toString();
//            String password = signupPassword.getText().toString();
//
//            if (name.length() < 3) {
//                signupName.setError("Name must be at least 3 characters long.");
//                return;
//            }
//
//            // Constraint: Email should end with ".com" or ".in"
//            if (!email.endsWith("@gmail.com") && !email.endsWith("@outlook.com")&& !email.endsWith("@yahoo.com")&& !email.endsWith("@ac.in")) {
//                signupEmail.setError("Invalid Email Format");
//                return;
//            }
//
//            // Constraint: Username should not be the same as the name
//            if (username.equalsIgnoreCase(name)) {
//                signupUsername.setError("Username cannot be the same as the name.");
//                return;
//            }
//            if (!username.startsWith("_")) {
//                signupUsername.setError("Username must begin with an underscore.");
//                return;
//            }
//
//            // Constraint: Password should not be the same as the name and be at least 6 characters long
//            if (password.equalsIgnoreCase(name)) {
//                signupPassword.setError("Password cannot be the same as the name.");
//                return;
//            }
//            if (password.length() < 6) {
//                signupPassword.setError("Password must be at least 6 characters long.");
//                return;
//            }
//            if (!password.matches(".*[A-Z].*")) {
//                signupPassword.setError("Password must contain at least one uppercase letter.");
//                return;
//            }
//            if (!password.matches(".*[a-z].*")) {
//                signupPassword.setError("Password must contain at least one lowercase letter.");
//                return;
//            }
//            if (!password.matches(".*\\d.*")) {
//                signupPassword.setError("Password must contain at least one digit.");
//                return;
//            }
//            if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?].*")) {
//                signupPassword.setError("Password must contain at least one special character.");
//            }
//
//            else{
//
//                reference.child(email.replace(".", ",")).addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            // The email address is already in use
//                            signupEmail.setError("This email is already registered.");
//                        } else {
//                            reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot usernameSnapshot) {
//                                    if (usernameSnapshot.exists()) {
//                                        // The username is already in use
//                                        signupUsername.setError("This username is already taken.");
//                                    } else {
//                                        // Username is also unique, proceed with registration
//                                        Helper helperClass = new Helper(name, email, username, password);
//                                        reference.child(email.replace(".", ",")).setValue(helperClass);
//
//                                        Toast.makeText(Signup.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(Signup.this, Login.class);
//                                        startActivity(intent);
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//                                    Toast.makeText(Signup.this, "ok", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(Signup.this, "ok", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//
//        });}