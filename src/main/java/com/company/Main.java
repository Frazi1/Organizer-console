package com.company;

import com.company.models.events.Event;
import com.company.models.events.EventTypes;
import com.company.repositories.EventRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.Helper.*;

public class Main {

    private final static String filePath = "events.json";

    public static void main(String[] args) {
        final List<String> menuList = new ArrayList<>();
        init(menuList);
        final EventRepository repository = new EventRepository(filePath);

        while(true){
            printList(menuList);
            int commandNumber = readInt();
            switch (commandNumber) {
                case 1: {
                    addEvent(repository);
                    break;
                }
                case 2: {
                    printEvents(repository);
                    break;
                }
                case 3: {
                    repository.saveChanges();
                }
            }
        }
    }

    private static void printEvents(EventRepository repository) {
        List<Event> events = repository.getItems();
        if(events.size() == 0){
            System.out.println("Empty\n");
        }
        else{
            printList(events);
        }
    }

    private static void addEvent(EventRepository repository) {
        List<EventTypes> eventTypesList = Arrays.asList(EventTypes.MeetingEvent,
                                                        EventTypes.BirthDayEvent);
        printList(eventTypesList);
        EventTypes eventType = eventTypesList.get(readInt() - 1);
        Event event = null;
        switch (eventType){
            case MeetingEvent: {
                event = createMeetingEvent();
                break;
            }
            case BirthDayEvent:{
                event = createBirthdayEvent();
                break;
            }
        }
        repository.add(event);
        System.out.println("Added: " + event.toString() + "\n");
    }

    private static void init(List<String> menuList) {
        menuList.add("1. Add event");
        menuList.add("2. Show events");
        menuList.add("3. Save to file");
    }
}
