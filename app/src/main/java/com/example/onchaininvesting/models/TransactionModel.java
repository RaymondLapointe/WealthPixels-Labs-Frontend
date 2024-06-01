package com.example.onchaininvesting.models;

import java.util.ArrayList;

public class TransactionModel {

    private ArrayList<EventModel> transactions;

    public TransactionModel() {
    }

    public TransactionModel(ArrayList<EventModel> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<EventModel> getTransactions() {
        return transactions;
    }
}