package com.example.onchaininvesting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onchaininvesting.R;
import com.example.onchaininvesting.adapters.TransactionAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    List<String> keys;
    TransactionAdapter adapter;

    public TransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new TransactionAdapter(keys);

        RecyclerView recyclerView = view.findViewById(R.id.rvTransactions);

        FirebaseFirestore.getInstance().collection("ComponentTransferred").addSnapshotListener((value, error) -> {
            if (error == null) {
                List<String> keys = new ArrayList<>();
                for (DocumentSnapshot doc : value) {
                    keys.add(doc.getId());
                }

                try {
                    TransactionAdapter adapter = new TransactionAdapter(keys);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                } catch (Exception ignored) {
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}