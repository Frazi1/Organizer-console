package com.company.controllers;

import com.company.models.Person;
import com.company.models.events.Event;
import com.company.repositories.DbEventsRepository;
import com.company.repositories.DbPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/events")
public class WebEventsController {
    private final DbEventsRepository dbEventsRepository;
    private final DbPersonRepository dbPersonRepository;

    @Autowired
    public WebEventsController(DbEventsRepository dbEventsRepository,
                               DbPersonRepository dbPersonRepository) {
        this.dbEventsRepository = dbEventsRepository;
        this.dbPersonRepository = dbPersonRepository;
    }

    @RequestMapping("add")
    public @ResponseBody String addEvent(@RequestParam String person,
                                         @RequestParam String description){
        Event e = new Event();
        Person p = new Person();
        p.setName(person);
        e.setPerson(p);
        e.setDescription(description);
        dbPersonRepository.save(p);
        dbEventsRepository.save(e);
        return e.toString();
    }
}
