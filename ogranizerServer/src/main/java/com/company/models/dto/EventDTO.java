package com.company.models.dto;

import com.company.models.events.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EventDTO {

    private Integer id;
    private String description;
    private String present;
    private PersonDTO person;
    private EventType eventType;
    //convert to time
    @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "hh:mm:ss")
    private Date birthHour;
    private Long date;

    public EventDTO(){
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Date getBirthHour() {
        return birthHour;
    }

    public void setBirthHour(Date birthHour) {
        this.birthHour = birthHour;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
