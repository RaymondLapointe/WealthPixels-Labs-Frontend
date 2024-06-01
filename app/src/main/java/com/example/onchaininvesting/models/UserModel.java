package com.example.onchaininvesting.models;

import java.util.List;

public class UserModel {

    private String name;
    private String email;
    private List<TransactionModel> transaction;

    public UserModel() {
    }

    public UserModel(String name, String email, List<TransactionModel> transaction) {
        this.name = name;
        this.email = email;
        this.transaction = transaction;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<TransactionModel> getTransaction() {
        return transaction;
    }
}
