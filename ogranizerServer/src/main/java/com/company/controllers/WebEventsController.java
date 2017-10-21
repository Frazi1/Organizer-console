package com.company.controllers;

import com.company.models.events.Meeting;
import com.company.repositories.DbBirthdayRepository;
import com.company.repositories.DbMeetingRepository;
import com.company.repositories.DbPersonRepository;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = MEETING_PATH + "/{id}", method = RequestMethod.GET)
    public Meeting getMeeting(@PathVariable Integer id) {
        return dbMeetingRepository.findOne(id);
    }

    @RequestMapping(value = MEETING_PATH, method = RequestMethod.GET)
    public Iterable<Meeting> getMeeting() {
        return dbMeetingRepository.findAll();
    }

    @RequestMapping(value = MEETING_PATH + "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeMeeting(@PathVariable Integer id) {
        this.dbMeetingRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = MEETING_PATH + "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMeeting(@RequestBody Meeting meeting,@PathVariable Integer id) {
        Meeting savedMeeting  = this.dbMeetingRepository.findOne(id);
        savedMeeting.setDescription(meeting.getDescription());
        savedMeeting.setPerson(meeting.getPerson());
        savedMeeting.setDate(meeting.getDate());
        this.dbMeetingRepository.save(savedMeeting);
        return ResponseEntity.ok(savedMeeting);
    }
}