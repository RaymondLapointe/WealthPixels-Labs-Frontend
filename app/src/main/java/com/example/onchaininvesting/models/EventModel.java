package com.example.onchaininvesting.models;

import java.util.Map;

public class EventModel {

    private Map<String, Object> events;

    public EventModel() {
    }

    public EventModel(Map<String, Object> events) {
        this.events = events;
    }

    public long getAmount() {
        return (long) events.get("amount");
    }

    public String getComponent() {
        return (String) events.get("component");
    }

    public int getPrice() {
        return (int) events.get("price");
    }

    public int getTimestamp() {
        return (int) events.get("timestamp");
    }

    public String getTo() {
        return (String) events.get("to");
    }
}

