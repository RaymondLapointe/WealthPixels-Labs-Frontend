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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    AppCompatButton signUp;
    EditText name, email, pass;
    String nameText, emailText, passText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextTextEmailAddress1);
        pass = findViewById(R.id.editTextTextPassword1);
        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(v -> {

            nameText = name.getText().toString();
            emailText = email.getText().toString();
            passText = pass.getText().toString();

            if (!nameText.isEmpty() || !emailText.isEmpty() || !passText.isEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailText, passText).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, String> userData = new HashMap<>();
                        userData.put("name", nameText);
                        userData.put("email", emailText);

                        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(userData).addOnSuccessListener(aVoid -> {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        });
                    } else {
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}