package com.company.models.events;

import com.company.models.Person;

import javax.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Long date;
    private String description;
    private String present;
    private int birthHour;
    private EventType eventType;

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDate() {
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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (getBirthHour() != event.getBirthHour()) return false;
        if (!getId().equals(event.getId())) return false;
        if (getDate() != null ? !getDate().equals(event.getDate()) : event.getDate() != null) return false;
        if (getDescription() != null ? !getDescription().equals(event.getDescription()) : event.getDescription() != null)
            return false;
        if (getPresent() != null ? !getPresent().equals(event.getPresent()) : event.getPresent() != null) return false;
        return getPerson() != null ? getPerson().equals(event.getPerson()) : event.getPerson() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPresent() != null ? getPresent().hashCode() : 0);
        result = 31 * result + getBirthHour();
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", description='").append(description).append('\'');
        sb.append(", present='").append(present).append('\'');
        sb.append(", birthHour=").append(birthHour);
        sb.append(", person=").append(person);
        sb.append('}');
        return sb.toString();
    }
}
