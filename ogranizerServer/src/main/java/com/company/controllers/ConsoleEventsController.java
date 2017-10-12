package com.company.controllers;

import com.company.models.events.Birthday;
import com.company.models.events.Event;
import com.company.models.events.EventTypes;
import com.company.repositories.FileEventRepository;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.company.Helper.*;
import static com.company.models.events.EventFields.*;

public class ConsoleEventsController {
    private final FileEventRepository repository;

    private static ConsoleEventsController instance;
    private static String filePath;

    private ConsoleEventsController() {
        repository = new FileEventRepository(filePath);
    }

    private void printValue(String valueName, String value) {
        System.out.println(valueName + ": " + value);
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

    public void editEvent(){
        printEvents();
        System.out.println("Select event id to edit");
        Integer id = readInt();
        System.out.println("Just press <ENTER> if you don't want to change value");
        Event event = repository.getItem(id);

        //Person
        printValue(PERSON, event.getPerson().getName());
        String name = readString();
        event.getPerson().setName(!Objects.equals(name, "") ? name : event.getPerson().getName());

        //Description
        printValue(DESCRIPTION, event.getDescription());
        String description = readString();
        event.setDescription(!Objects.equals(description, "") ? description : event.getDescription());

        //Date
        printValue(DATE, event.getDate().toString());
        String dateString = readString();
        event.setDate(!Objects.equals(dateString, "") ? parseLocalDate(dateString) : event.getDate());

        if(event instanceof Birthday){
            Birthday birthday = (Birthday) event;

            //Present
            printValue(PRESENT, birthday.getPresent());
            String present = readString();
            birthday.setPresent(!Objects.equals(present, "") ? present : birthday.getPresent());

            //BirthHour
            printValue(BIRTH_HOUR, String.valueOf(birthday.getBirthHour()));
            String  birthHourString = readString();

            birthday.setBirthHour(!Objects.equals(birthHourString, "")
                    ? Integer.parseInt(birthHourString)
                    : birthday.getBirthHour());
        }
        System.out.println("Id " + id + " changed\n");
    }

    public void removeEvent() {
        printEvents();
        System.out.println("Choose event ID");
        int id = readInt();
        boolean removed = repository.remove(id);
        if(removed) System.out.println("Removed: " + id + "\n");
        else System.out.println("Id " + id + " doesn't exist\n");
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
