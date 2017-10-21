package com.company.controllers;

import com.company.models.Person;
import com.company.models.events.Meeting;
import com.company.repositories.DbBirthdayRepository;
import com.company.repositories.DbMeetingRepository;
import com.company.repositories.DbPersonRepository;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

import static com.company.Config.MEETING_PATH;
import static com.company.Config.SERVER_PATH;

@RestController
@RequestMapping("api/events")
@CrossOrigin(origins = SERVER_PATH)
public class WebEventsController {

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

    @RequestMapping(value = MEETING_PATH, method = RequestMethod.POST)
    @Cascade(CascadeType.ALL)
    public ResponseEntity<?> addMeetingEvent(@RequestBody Meeting meeting){
        this.dbMeetingRepository.save(meeting);
        return ResponseEntity.accepted().body(meeting);
    }

    @RequestMapping(value = MEETING_PATH, method = RequestMethod.GET)
    public Iterable<Meeting> getMeeting() {
        return dbMeetingRepository.findAll();
    }

    @RequestMapping(value = "meeting1", method = RequestMethod.GET)
    public String testMeeting() {
        Meeting m = new Meeting();
        Person p = new Person();
        p.setName("Valera");
        m.setPerson(p);
        m.setDescription("test meeting");
        m.setDate(Instant.now().getEpochSecond());
        dbMeetingRepository.save(m);
        return m.toString();
    }
}
