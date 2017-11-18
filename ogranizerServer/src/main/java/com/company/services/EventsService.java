package com.company.services;

import com.company.models.Person;
import com.company.models.dto.EventDTO;
import com.company.models.events.Event;
import com.company.repositories.DbEventsRepository;
import com.company.repositories.DbPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service()
public class EventsService {
    private final DbPersonRepository dbPersonRepository;
    private final DbEventsRepository dbEventsRepository;
    private final ModelConverterService modelConverterService;

    @Autowired
    public EventsService(DbPersonRepository dbPersonRepository,
                         DbEventsRepository dbEventsRepository,
                         ModelConverterService modelConverterService) {
        this.dbPersonRepository = dbPersonRepository;
        this.dbEventsRepository = dbEventsRepository;
        this.modelConverterService = modelConverterService;
    }

    public void addEvent(EventDTO eventDTO) {
        Event event = modelConverterService.getEventFromEventDTO(eventDTO);
        Person person = event.getPerson();
        dbPersonRepository.save(person);
        dbEventsRepository.save(event);
    }

    public Iterable<EventDTO> getEvents(){
        return StreamSupport.stream(dbEventsRepository.findAll().spliterator(), false)
                .map(modelConverterService::getEventDTOFromEvent).collect(Collectors.toList());
    }

    public EventDTO getEventById(Integer id) {
        return modelConverterService.getEventDTOFromEvent(dbEventsRepository.findOne(id));
    }

    public void removeEventById(Integer id) {
        dbEventsRepository.delete(id);
    }

    public EventDTO updateEventById(EventDTO eventDTO, Integer id){
        Event savedEvent  = this.dbEventsRepository.findOne(id);
        savedEvent.setDescription(eventDTO.getDescription());
        savedEvent.getPerson().setName(eventDTO.getPerson().getName());
        Date date = new Date(eventDTO.getDate());
        savedEvent.setDate(date);
        savedEvent.setBirthHour(eventDTO.getBirthHour());
        savedEvent.setPresent(eventDTO.getPresent());
        savedEvent.setEventType(eventDTO.getEventType());
        this.dbEventsRepository.save(savedEvent);
        return modelConverterService.getEventDTOFromEvent(savedEvent);
    }
}
