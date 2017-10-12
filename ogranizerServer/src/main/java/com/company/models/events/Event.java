package com.company.models.events;

import com.company.models.Person;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private LocalDate date;
    private String description;
    @OneToOne
    private Person person;

    public static String getDatePattern(){
        return "dd-mm-yyyy";
    }
    public static char getDelimiter() {
        return '\n';
    }

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (!getId().equals(event.getId())) return false;
        if (!getDate().equals(event.getDate())) return false;
        if (!getDescription().equals(event.getDescription())) return false;
        return getPerson().equals(event.getPerson());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPerson().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return ", date=" + date +
                ", description='" + description + '\'' +
                ", person=" + person;
    }
}
