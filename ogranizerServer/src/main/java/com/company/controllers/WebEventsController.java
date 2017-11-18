package com.company.controllers;

import com.company.models.events.Event;
import com.company.repositories.DbEventsRepository;
import com.company.repositories.DbPersonRepository;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Config.*;

@RestController
@RequestMapping(API_EVENTS)
@CrossOrigin(origins = CLIENT_URL)
public class WebEventsController {

    private final DbPersonRepository dbPersonRepository;
    private final DbEventsRepository dbEventsRepository;

    @Autowired
    public WebEventsController(DbPersonRepository dbPersonRepository,
                               DbEventsRepository dbEventsRepository) {
        this.dbPersonRepository = dbPersonRepository;
        this.dbEventsRepository = dbEventsRepository;
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> addEvent(@RequestBody Event event){
        this.dbPersonRepository.save(event.getPerson());
        this.dbEventsRepository.save(event);
        return ResponseEntity.accepted().body(event);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable Integer id) {
        return dbEventsRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Event> getEvent() {
        return dbEventsRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeEvent(@PathVariable Integer id) {
        this.dbEventsRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEvent(@RequestBody Event event,@PathVariable Integer id) {
        Event savedEvent  = this.dbEventsRepository.findOne(id);
        savedEvent.setDescription(event.getDescription());
        savedEvent.getPerson().setName(event.getPerson().getName());
        savedEvent.setDate(event.getDate());
        savedEvent.setBirthHour(event.getBirthHour());
        savedEvent.setPresent(event.getPresent());
        savedEvent.setEventType(event.getEventType());
        this.dbEventsRepository.save(savedEvent);
        return ResponseEntity.ok(savedEvent);
    }
}