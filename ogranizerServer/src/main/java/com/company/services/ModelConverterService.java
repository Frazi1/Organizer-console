package com.company.services;

import com.company.models.Person;
import com.company.models.dto.EventDTO;
import com.company.models.dto.PersonDTO;
import com.company.models.events.Event;
import org.springframework.stereotype.Service;

@Service()
public class ModelConverterService {

    public Person getPersonFromPersonDTO(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        return person;
    }

    public PersonDTO getPersonDTOFromPerson(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        return personDTO;
    }

    public Event getEventFromEventDTO(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setBirthHour(eventDTO.getBirthHour());
        event.setDate(eventDTO.getDate());
        event.setDescription(eventDTO.getDescription());
        event.setEventType(eventDTO.getEventType());
        event.setPerson(getPersonFromPersonDTO(eventDTO.getPerson()));
        event.setPresent(eventDTO.getPresent());
        return event;
    }

    public EventDTO getEventDTOFromEvent(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setBirthHour(event.getBirthHour());
        eventDTO.setDate(event.getDate());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setEventType(event.getEventType());
        eventDTO.setPerson(getPersonDTOFromPerson(event.getPerson()));
        eventDTO.setPresent(event.getPresent());
        return eventDTO;
    }
}
