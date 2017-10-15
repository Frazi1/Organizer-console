package com.company.controllers;

import com.company.models.Person;
import com.company.models.events.Birthday;
import com.company.models.events.Meeting;
import com.company.repositories.DbBirthdayRepository;
import com.company.repositories.DbMeetingRepository;
import com.company.repositories.DbPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @CrossOrigin(origins = SERVER_PATH)
//    @RequestMapping("addMeeting")
//    public ResponseEntity<?> addMeetingEvent(@RequestParam String person,
//                                             @RequestParam String description){
//        Meeting e = new Meeting();
//        Person p = new Person();
//        p.setName(person);
//        e.setPerson(p);
//        e.setDescription(description);
//        dbPersonRepository.save(p);
//        dbMeetingRepository.save(e);
//        return ResponseEntity.noContent().build();
//    }
    @RequestMapping("addMeeting")
    public ResponseEntity<?> addMeetingEvent(@RequestBody Meeting meeting){
        meeting.setId(null);
        this.dbMeetingRepository.save(meeting);
        return ResponseEntity.accepted().body(meeting);
    }


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

    @RequestMapping("getBirthday")
    public Iterable<Birthday> getBirthday(){
        return dbBirthdayRepository.findAll();
    }

    @RequestMapping("getMeeting")
    public Iterable<Meeting> getMeeting() {
        return dbMeetingRepository.findAll();
    }
}
