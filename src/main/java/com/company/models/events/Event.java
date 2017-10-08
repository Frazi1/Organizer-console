package com.company.models.events;

import com.company.models.Person;

import java.util.Date;

public class Event {
    private Integer id;
    private Date date;
    private String description;
    private Person person;

    public Event() {
    }

    public static char getDelimiter() {
        return '\n';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static String getDatePattern(){
        return "dd-mm-yyyy";
    }
}
