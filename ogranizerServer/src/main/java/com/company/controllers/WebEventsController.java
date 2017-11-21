package com.company.controllers;

import com.company.models.dto.EventDTO;
import com.company.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Config.API_EVENTS;
import static com.company.Config.CLIENT_URL;

@RestController
@RequestMapping(API_EVENTS)
@CrossOrigin(origins = CLIENT_URL)
public class WebEventsController {

    private final EventsService eventsService;

    @Autowired
    public WebEventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> addEvent(@RequestBody EventDTO eventDTO){
        if(eventDTO.getPerson() == null
                || eventDTO.getEventType() == null){
            return ResponseEntity.badRequest().build();
        }
        eventsService.addEvent(eventDTO);
        return ResponseEntity.accepted().body(eventDTO);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventDTO getEvent(@PathVariable Integer id) {
        return eventsService.getEventById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<EventDTO> getEvent() {
        return eventsService.getEvents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeEvent(@PathVariable Integer id) {
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        eventsService.removeEventById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEvent(@RequestBody EventDTO eventDTO, @PathVariable Integer id) {
        if(id == null
                || eventDTO.getPerson() == null
                || eventDTO.getEventType() == null){
            return ResponseEntity.badRequest().build();
        }
        EventDTO result = eventsService.updateEventById(eventDTO, id);
        if(result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}