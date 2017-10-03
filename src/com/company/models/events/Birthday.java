package com.company.models.events;

public class Birthday extends Event {
    private String present;
    private int birthHour;

    public Birthday() {
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public int getBirthHour() {
        return birthHour;
    }

    public void setBirthHour(int birthHour) {
        if(birthHour > 0 && birthHour < 24) {
            this.birthHour = birthHour;
        }
        else {
            this.birthHour = 0;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getPerson())
                .append(Event.getDelimiter())
                .append(getDescription())
                .append(Event.getDelimiter())
                .append(getPresent())
                .append(Event.getDelimiter())
                .append(getDate())
                .append(Event.getDelimiter())
                .append(getBirthHour())
                .toString();
    }
}
