package com.company.menu.states;

import com.company.models.events.Birthday;
import com.company.models.events.Event;
import com.company.models.events.EventTypes;
import com.company.models.events.Meeting;
import com.company.repositories.EventRepository;

import java.time.Instant;
import java.util.Date;

public class EventsAddState extends MenuState {

    public EventsAddState(MenuState previousState) {
        super(previousState);
    }

    @Override
    public void run(EventRepository eventRepository) {
        printEvents();
        Event event = selectEvent();
        eventRepository.add(event);
    }

    private void printEvents() {
        for (Integer i = 0; i < getEventTypesList().size(); i++) {
            System.out.println(i+1 + ". " + getEventTypesList().get(i));
        }
    }

    private Event selectEvent() {
        Integer commandNumber = readCommandNumber();
        EventTypes eventType = getEventTypesList().get(commandNumber);
        switch (eventType) {
            case BirthDayEvent:
                return new Birthday();
            case MeetingEvent:
                return new Meeting();
        }
        return null;
    }

    private Event buildMeeting(Meeting instance) {
        instance.setDate(Date.from(Instant.now()));
        instance.setDescription("TEST");
        return instance;
    }

    private Event buildBirthday(Birthday istance) {
            //TODO i
        return null;
    }

    @Override
    public String toString() {
        return "Add event";
    }
}
