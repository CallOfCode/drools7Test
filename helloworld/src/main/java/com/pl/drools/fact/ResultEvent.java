package com.pl.drools.fact;

import java.util.ArrayList;
import java.util.List;

public class ResultEvent {
    private List<String> events = new ArrayList<>();

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }
}
