package com.company.services;

import com.company.models.Person;
import com.company.models.dto.EventDTO;
import com.company.models.dto.PersonDTO;
import com.company.models.events.Event;
import com.company.models.events.EventType;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service()
public class ModelConverterService {

    public Person convertDtoToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        return person;
    }

    public PersonDTO convertPersonToDto(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        return personDTO;
    }

    public Event convertDtoToEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setBirthHour(eventDTO.getBirthHour());
        event.setDate(
                convertLongToSqlDate(eventDTO.getDate()));
        event.setDescription(eventDTO.getDescription());
        event.setEventType(
                convertEventTypeToString(eventDTO.getEventType()));
        event.setPerson(
                convertDtoToPerson(eventDTO.getPerson()));
        event.setPresent(eventDTO.getPresent());
        return event;
    }

    public EventDTO convertEventToDto(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setBirthHour(event.getBirthHour());
        eventDTO.setDate(
                convertSqlDateToLong(event.getDate()));
        eventDTO.setDescription(event.getDescription());
        eventDTO.setEventType(
                convertStringToEventType(event.getEventType()));
        eventDTO.setPerson(
                convertPersonToDto(event.getPerson()));
        eventDTO.setPresent(event.getPresent());
        return eventDTO;
    }

    public java.util.Date convertLongToSqlDate(Long dateLong) {
        return new java.util.Date(dateLong);
    }

    public Long convertSqlDateToLong(Date date) {
        return date.getTime();
    }

    public String convertEventTypeToString(EventType eventType) {
        return eventType.toString();
    }

    public EventType convertStringToEventType(String eventTypeString) {
        return EventType.valueOf(eventTypeString);
    }
}