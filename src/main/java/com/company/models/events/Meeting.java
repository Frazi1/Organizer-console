package com.company.models.events;

public class Meeting extends Event {
    public Meeting() {
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getId())
                .append(". ")
                .append(getPerson())
                .append(" ")
                .append(getDate())
                .append(" ")
                .append(getDescription())
                .toString();
    }
}
