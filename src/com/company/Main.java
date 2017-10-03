package com.company;

import com.company.models.Person;
import com.company.models.events.Birthday;
import com.company.models.events.Event;
import com.company.models.events.EventTypes;
import com.company.models.events.Meeting;
import com.company.repositories.EventRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        List<String> menuList = new ArrayList<>();
        init(menuList);
        EventRepository repository = new EventRepository();

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
            }
        }
    }

    private static int readInt() {
        return new Scanner(System.in).nextInt();
    }

    private static String readString() {
        return new Scanner(System.in).nextLine();
    }

    private static void printEvents(EventRepository repository) {

    }

    private static void addEvent(EventRepository repository) {
        List<EventTypes> eventTypesList = Arrays.asList(EventTypes.MeetingEvent,
                                                        EventTypes.BirthDayEvent);
        printList(eventTypesList);
        EventTypes eventType = eventTypesList.get(readInt() - 1);
        switch (eventType){
            case MeetingEvent: {
                repository.add(createMeetingEvent());
                break;
            }
            case BirthDayEvent:{
                repository.add(createBirthdayEvent());
                break;
            }
        }
    }

    private static Birthday createBirthdayEvent() {
        Birthday birthday = new Birthday();

        //Person
        Person person = new Person();
        out.println("Person name");
        person.setName(readString());
        birthday.setPerson(person);

        //Desc
        out.println("Desc");
        birthday.setDescription(readString());

        //Birth hour
        out.println("Birth hour");
        birthday.setBirthHour(readInt());

        //Date
        out.println("Date " + Event.getDatePattern() );
        birthday.setDate(parseDate(readString(),Event.getDatePattern()));

        //Presetnt
        out.println("Present");
        birthday.setPresent(readString());

        out.println(birthday);
        return birthday;
    }

    private static Meeting createMeetingEvent() {
        Meeting meeting = new Meeting();

        //Person
        Person person = new Person();
        out.println("Person name");
        person.setName(readString());
        meeting.setPerson(person);

        //Desc
        out.println("Description");
        meeting.setDescription(readString());

        //Date
        out.println("Date");
        meeting.setDate(parseDate(readString(), Event.getDatePattern()));

        out.println(meeting.toString());
        return meeting;
    }

    private static Date parseDate(String source, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static void init(List<String> menuList) {
        menuList.add("Add event");
        menuList.add("Show events");
    }

    private static <T> void printList(List<T> list) {
        for (int i = 0; i < list.size(); i++){
            out.println(i+1 + ". " + list.get(i).toString());
        }
    }
}
