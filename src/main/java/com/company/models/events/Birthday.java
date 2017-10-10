package com.company.models.events;

import javax.persistence.Entity;

@Entity
public class Birthday extends Event {
    private String present;
    private int birthHour;

    public Birthday() { }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Birthday)) return false;
        if (!super.equals(o)) return false;

        Birthday birthday = (Birthday) o;

        if (getBirthHour() != birthday.getBirthHour()) return false;
        return getPresent().equals(birthday.getPresent());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getPresent().hashCode();
        result = 31 * result + getBirthHour();
        return result;
    }

    @Override
    public String toString() {
        return  getId() + ". " + "Birthday:" + "present='" + present + '\'' +
                ", birthHour=" + birthHour +
                super.toString();
    }
}
