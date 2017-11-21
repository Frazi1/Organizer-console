package com.company.models.events;

import com.company.models.Person;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "Description")
    private String description;

    @Column(name = "Present")
    private String present;

    @Column(name = "BirthTime")
    private int birthHour;

    @Column(name = "EventType")
    private String eventType;

    @OneToOne()
    @JoinColumn(name = "PersonId")
    private Person person;

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.birthHour = birthHour;
    }

    public String  getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return event.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
