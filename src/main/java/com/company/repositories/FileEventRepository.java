package com.company.repositories;

import com.company.models.events.Event;
import com.company.models.json.Json;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileEventRepository implements Repository<Event> {
    private List<Event> events;
    private final String filePath;

    public FileEventRepository(String filePath) {
        this.events = new ArrayList<>();
        this.filePath = filePath;
        events.addAll(loadEvents());
    }

    private List<Event> loadEvents(){
        Gson gson = Json.getGson();
        try {
            String json = readFromFile();
                Event[] eventsArray = gson.fromJson(json, Event[].class);
            return Arrays.asList(eventsArray);
        }
        catch (IOException ignored) {
        }
        return new ArrayList<>();
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

    private String readFromFile() throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();
        Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8)
                .forEach(jsonStringBuilder::append);
        return jsonStringBuilder.toString();
    }

    private void add(Integer id, Event item)
    {
        item.setId(id);
        events.add(item);
    }

    @Override
    public Event getItem(Integer id) {
        return getItem(item-> item.getId() == id);
    }

    @Override
    public Event getItem(Predicate<Event> predicate) {
        return events.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
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
        add(events.size() + 1, item);
    }

    @Override
    public void edit(Integer id, Event newObject) {
        if(newObject.equals(getItem(id))) return;
        remove(id);
        add(id, newObject);
    }

    @Override
    public boolean remove(Integer id) {
        return remove(event -> event.getId().equals(id));
    }

    @Override
    public boolean remove(Predicate<Event> predicate) {
        return events.removeIf(predicate);

    }

    @Override
    public void saveChanges() {
        Gson gson = Json.getGson();
        String json = gson.toJson(events);
        System.out.println(json);
        writeToFile(json);
    }

    public List<Integer> getEventsYears() {
        List<Integer> years = new ArrayList<>();
        events.forEach(e -> {
            Integer year = e.getDate().getYear();
            if (!years.contains(year)) {
                years.add(year);
            }
        });
        return years;
    }

    public List<Month> getEventsMonths() {
        List<Month> months = new ArrayList<>();
        events.forEach(e -> {
            Month month = e.getDate().getMonth();
            if (!months.contains(month)) {
                months.add(month);
            }
        });
        return months;
    }
}
