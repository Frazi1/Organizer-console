package com.company.repositories;

import com.company.models.events.Birthday;
import com.company.models.events.Event;
import com.company.models.events.Meeting;
import com.company.models.json.Json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.tree.ExpandVetoException;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventRepository implements Repository<Event> {
    private List<Event> events;
    private final String filePath;

    public EventRepository(String filePath) {
//        this.events = new ArrayList<Event>();
        this.filePath = filePath;
        this.events = loadFromFile();
    }

    private List<Event> loadFromFile(){
        Gson gson = Json.getGson();
        String json = readFromFile();
        Event[] eventsArray = gson.fromJson(json, Event[].class);
//        Arrays.stream(eventsArray)
//                    .forEach(event -> System.out.println(event instanceof Meeting || event instanceof Birthday));
        return Arrays.asList(eventsArray);
    }

    @Override
    public Event getItem(Integer id) {
        return getItem(item-> Objects.equals(item.getId(), id));
    }

    @Override
    public Event getItem(Predicate<Event> predicate) {
        return null;
    }

    @Override
    public List<Event> getItems() {
        return Collections.unmodifiableList(events);
    }

    @Override
    public List<Event> getItems(Predicate<Event> predicate) {
        List<Event> eventList = events.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(eventList);
    }

    @Override
    public void add(Event item) {
        events.add(item);
    }

    @Override
    public void remove(Event item) {
        events.remove(item);
    }

    @Override
    public void remove(Predicate<Event> predicate) {
        events = events.stream()
                .filter(predicate.negate())
                .collect(Collectors.toList());
//        events.forEach((event)->{if(predicate.test(event)) remove(event);});
    }

    @Override
    public void saveChanges() {
        Gson gson = Json.getGson();
        String json = gson.toJson(events);
        System.out.println(json);
        writeToFile(json);
    }

    private void writeToFile(String jsonString){
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(){
        StringBuilder jsonStringBuilder = new StringBuilder();
        try {
            Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8)
                                        .forEach(jsonStringBuilder::append);
        } catch (IOException ignored) {
        }
        return jsonStringBuilder.toString();
    }
}
