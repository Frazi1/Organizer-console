package com.company.controllers;

import com.company.models.Person;
import com.company.models.events.Birthday;
import com.company.models.events.Event;
import com.company.models.events.Meeting;
import com.company.models.json.Json;
import com.company.repositories.DbBirthdayRepository;
import com.company.repositories.DbMeetingRepository;
import com.company.repositories.DbPersonRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/events")
public class WebEventsController {
    private static final String SERVER_PATH = "http://localhost:4200";

    private final DbMeetingRepository dbMeetingRepository;
    private final DbPersonRepository dbPersonRepository;
    private final DbBirthdayRepository dbBirthdayRepository;

    @Autowired
    public WebEventsController(DbMeetingRepository dbMeetingRepository,
                               DbPersonRepository dbPersonRepository,
                               DbBirthdayRepository dbBirthdayRepository) {
        this.dbMeetingRepository = dbMeetingRepository;
        this.dbPersonRepository = dbPersonRepository;
        this.dbBirthdayRepository = dbBirthdayRepository;
    }

    @CrossOrigin(origins = SERVER_PATH)
    @RequestMapping("addMeeting")
    public ResponseEntity<?> addMeetingEvent(@RequestParam String person,
                                             @RequestParam String description){
        Meeting e = new Meeting();
        Person p = new Person();
        p.setName(person);
        e.setPerson(p);
        e.setDescription(description);
        dbPersonRepository.save(p);
        dbMeetingRepository.save(e);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = SERVER_PATH)
    @RequestMapping("addBirthday")
    public ResponseEntity<?> addBirthdayEvent(@RequestParam String person,
                                              @RequestParam String description,
                                              @RequestParam String present,
                                              @RequestParam Integer birthHour){
        Person p = new Person();
        p.setName(person);

        Birthday birthday = new Birthday();
        birthday.setBirthHour(birthHour);
        birthday.setPresent(present);
        birthday.setDescription(description);

        dbPersonRepository.save(p);
        dbBirthdayRepository.save(birthday);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = SERVER_PATH)
    @RequestMapping("getBirthday")
    public Iterable<Birthday> getBirthday(){
        return dbBirthdayRepository.findAll();
    }

    @CrossOrigin(origins = SERVER_PATH)
    @RequestMapping("getMeeting")
    public Iterable<Meeting> getMeeting() {
        return dbMeetingRepository.findAll();
    }
}
