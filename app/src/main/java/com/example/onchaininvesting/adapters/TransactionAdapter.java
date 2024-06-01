package com.example.onchaininvesting.adapters;

import android.annotation.SuppressLint;
import android.util.Log;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Instant;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onchaininvesting.R;
import com.example.onchaininvesting.models.EventModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<String> keys;
    private ArrayList bruh;
    public TransactionAdapter(List<String> keys) {
        this.keys = keys;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item_assets, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirebaseFirestore.getInstance().collection("ComponentTransferred").document(keys.get(position)).get().addOnSuccessListener(documentSnapshot -> {

            ArrayList<Map<String, Object>> events = (ArrayList<Map<String, Object>>) documentSnapshot.get("events");

            for (int i = 0; i < events.size() - 1; i++ ) {
                Map<String, Object> event1 = events.get(i);
                Map<String, Object> event2 = events.get(i+1);

                double amount1Double = (double) event1.get("amount");
                BigDecimal amount1BigDecimal = BigDecimal.valueOf(amount1Double);
                BigDecimal amount1Divided = amount1BigDecimal.divide(BigDecimal.TEN.pow(18));
                BigDecimal roundedAmount1 = amount1Divided.setScale(4, RoundingMode.HALF_UP);
                String amount1 = roundedAmount1.toString();

                Long price1Double = (Long) event1.get("price");
                BigDecimal price1BigDecimal = BigDecimal.valueOf(price1Double);
                BigDecimal price1Divided = price1BigDecimal.divide(BigDecimal.TEN.pow(8));
                BigDecimal roundedPrice1 = price1Divided.setScale(4, RoundingMode.HALF_UP);
                String price1 = roundedPrice1.toString();

                String component1 = (String) event1.get("component");
                String timestamp1 = convertTimeStamp(String.valueOf(event1.get("timestamp")));


                double amount2Double = (double) event2.get("amount");
                BigDecimal amount2BigDecimal = BigDecimal.valueOf(amount2Double);
                BigDecimal amount2Divided = amount2BigDecimal.divide(BigDecimal.TEN.pow(18));
                BigDecimal roundedAmount2 = amount2Divided.setScale(4, RoundingMode.HALF_UP);
                String amount2 = roundedAmount2.toString();

                Long price2Double = (Long) event2.get("price");
                BigDecimal price2BigDecimal = BigDecimal.valueOf(price2Double);
                BigDecimal price2Divided = price2BigDecimal.divide(BigDecimal.TEN.pow(8));
                BigDecimal roundedPrice2 = price2Divided.setScale(4, RoundingMode.HALF_UP);
                String price2 = roundedPrice2.toString();

                String component2 = (String) event2.get("component");
                String timestamp2 = convertTimeStamp(String.valueOf(event2.get("timestamp")));

                holder.component1.setText("Component: " + component1);
                holder.amount1.setText("Amount: " + amount1);
                holder.price1.setText("Price: " + price1);
                holder.timestamp1.setText("Amount: " + timestamp1);

                holder.component2.setText("Component: " + component2);
                holder.amount2.setText("Amount: " + amount2);
                holder.price2.setText("Price: " + price2);
                holder.timestamp2.setText("Timestamp: " + timestamp2);

                i++;
            }
        });
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView amount1, amount2;
        TextView price1, price2;
        TextView component1, component2;
        TextView timestamp1, timestamp2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            amount1 = itemView.findViewById(R.id.amount1);
            amount2 = itemView.findViewById(R.id.amount2);
            price1 = itemView.findViewById(R.id.price1);
            price2 = itemView.findViewById(R.id.price2);
            component1 = itemView.findViewById(R.id.component1);
            component2 = itemView.findViewById(R.id.component2);
            timestamp1 = itemView.findViewById(R.id.timestamp1);
            timestamp2 = itemView.findViewById(R.id.timestamp2);
        }
    }

    public String convertTimeStamp(String timestampString) {
        Instant instant = Instant.ofEpochSecond(Long.parseLong(timestampString));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss").withZone(ZoneOffset.UTC);
        return formatter.format(instant);
    }
}
