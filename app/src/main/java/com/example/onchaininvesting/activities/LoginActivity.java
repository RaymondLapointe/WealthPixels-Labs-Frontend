package com.example.onchaininvesting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.onchaininvesting.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    AppCompatButton signIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(v -> {

            String emailText = email.getText().toString();
            String passText = pass.getText().toString();

            if (!emailText.isEmpty() || !passText.isEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailText, passText).addOnSuccessListener(authResult -> {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }).addOnFailureListener(e -> Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }
}
