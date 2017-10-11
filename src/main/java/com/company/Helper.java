package com.company;

import com.company.models.Person;
import com.company.models.events.Birthday;
import com.company.models.events.Event;
import com.company.models.events.Meeting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

public class Helper {

    public static int readInt() {
        return new Scanner(System.in).nextInt();
    }

    public static String readString() {
        return new Scanner(System.in).nextLine();
    }

    public static LocalDate parseLocalDate(String dateString) {
        return parseDate(dateString, Event.getDatePattern())
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Birthday createBirthdayEvent() {
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
        birthday.setDate(
                parseLocalDate(readString())
        );

        //Present
        out.println("Present");
        birthday.setPresent(readString());

        return birthday;
    }

    public static Meeting createMeetingEvent() {
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
        meeting.setDate(
                parseLocalDate(readString())
        );

        return meeting;
    }

    public static Date parseDate(String source, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static <T> void printList(Iterable<T> iterable){
        iterable.forEach(System.out::println);
        System.out.println();
    }

    public static void printValue(String valueName, String value) {
        System.out.println(valueName + ": " + value);
    }

}
