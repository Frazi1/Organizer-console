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

    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    public static String getDatePattern(){
        return "dd-mm-yyyy";
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;

        Event event = (Event) o;

        if (!getId().equals(event.getId())) return false;
//        if (!getDate().equals(event.getDate())) return false;
        if (!getDescription().equals(event.getDescription())) return false;
        return getPerson().equals(event.getPerson());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
//        result = 31 * result + getDate().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPerson().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return
//                ", date=" + date +
                ", description='" + description + '\'' +
                ", person=" + person;
    }
}
