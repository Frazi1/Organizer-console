package com.company.controllers;

import com.company.models.events.Event;
import com.company.models.events.EventTypes;
import com.company.repositories.EventRepository;

import java.lang.reflect.Modifier;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.company.Helper.*;

public class ConsoleEventsController {
    private static ConsoleEventsController instance;
    private static String filePath;

    private final EventRepository repository;

    private ConsoleEventsController() {
        repository = new EventRepository(filePath);
    }

    public static ConsoleEventsController getInstance() {
        if(instance == null){
            instance = new ConsoleEventsController();
        }
        return instance;
    }

    public static void setFilePath(String filePath) {
        ConsoleEventsController.filePath = filePath;
    }

    public void printEvents() {
        List<Event> events = repository.getItems();
        if(events.size() == 0){
            System.out.println("Empty\n");
        }
        else{
            printList(events);
        }
    }

    public void addEvent() {
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

    public void saveChanges() {
        repository.saveChanges();
    }

    public void removeEvent() {
        printEvents();
        System.out.println("Choose event ID");
        int id = readInt();
        repository.remove(id);
        System.out.println("Removed: " + id);
    }

    public void printEventYears(){
        List<Integer> years = repository.getEventsYears();
        printList(years);
        System.out.println("Input year id");
        Integer number = readInt();
        printList(
                repository.getItems(event ->
                        event.getDate().getYear() == years.get(number - 1)));
    }

    public void printEventMonths(){
        List<Month> months = repository.getEventsMonths();
        printList(months);
        System.out.println("Input month id");
        Integer number = readInt();
        printList(
                repository.getItems(event ->
                event.getDate().getMonth() == months.get(number - 1))
        );
    }
}
