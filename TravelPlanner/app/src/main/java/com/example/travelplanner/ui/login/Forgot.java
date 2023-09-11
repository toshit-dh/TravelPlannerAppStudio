package com.example.travelplanner.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelplanner.R;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        auth = FirebaseAuth.getInstance();

        Button buttonreset = findViewById(R.id.btnyes);
        Button buttoncancel = findViewById(R.id.btnno);
        EditText emailBox = findViewById(R.id.emailBox);

        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailBox.getText().toString();
                if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    emailBox.setError("Enter Your Registered Email");
                    return;
                }

                auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(Forgot.this, "Check your email", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Forgot.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Forgot.this, Login.class));
            }
        });

}
}
