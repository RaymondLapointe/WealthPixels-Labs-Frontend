package com.example.onchaininvesting.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onchaininvesting.R;
import com.example.onchaininvesting.activities.WelcomeActivity;
import com.example.onchaininvesting.adapters.TransactionAdapter;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.profle_name);
        TextView email = view.findViewById(R.id.profle_email);

        FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getUid()).get().addOnSuccessListener(documentSnapshot -> {
            name.setText(documentSnapshot.getString("name"));
            email.setText(documentSnapshot.getString("email"));
        });

        view.findViewById(R.id.logout_button).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(), WelcomeActivity.class));
            getActivity().finish();
        });
    }
}
