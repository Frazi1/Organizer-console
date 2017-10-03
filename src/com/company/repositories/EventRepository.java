package com.company.repositories;

import com.company.models.events.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EventRepository implements Repository<Event> {
    private List<Event> events;

    public EventRepository() {
        events = new ArrayList<Event>();
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
    public Iterable<Event> getItems() {
        return Collections.unmodifiableList(events);
    }

    @Override
    public Iterable<Event> getItems(Predicate<Event> predicate) {
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
//        events = events.stream()
//                .filter(predicate)
//                .collect(Collectors.toList());
        events.forEach((event)->{if(predicate.test(event)) remove(event);});
    }
}
