package com.example.onchaininvesting.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.onchaininvesting.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        AppCompatButton signIn = findViewById(R.id.sign_in);
        AppCompatButton signUp = findViewById(R.id.sign_up);

        signIn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), LoginActivity.class)));
        signUp.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
    }
}
