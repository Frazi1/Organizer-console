package com.company.models.events;

import javax.persistence.Entity;

@Entity
public class Meeting extends Event {
    public Meeting() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getId() + ". " + "Meeting: " + super.toString();
    }
}
