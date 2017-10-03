package com.company.menu.states;

import com.company.models.events.EventTypes;
import com.company.repositories.EventRepository;

import java.io.IOException;
import java.util.*;

public abstract class MenuState extends State<EventRepository> {
    private final MenuState previousState;
    private List<MenuState> availableStates;
    private List<EventTypes> eventTypesList;

    public MenuState(MenuState previousState) {
        this.availableStates = new ArrayList<>();

        this.eventTypesList = Arrays.asList(EventTypes.BirthDayEvent,
                                       EventTypes.MeetingEvent);
        this.previousState = previousState;
    }

    protected Integer readCommandNumber() {
        return readInteger();
    }

    public int readInteger() {
        return new Scanner(System.in).nextInt();
    }

    protected List<EventTypes> getEventTypesList() {
        return this.eventTypesList;
    }

    protected void addState(MenuState menuState){
        this.availableStates.add(menuState);
    }

    public void printMenu() {
        for (Integer i = 0; i < getAvailableStates().size(); i++){
            System.out.println(i+1 + ". " + getAvailableStates().get(i).toString());
        }
    }

    public List<MenuState> getAvailableStates() {
        return Collections.unmodifiableList(availableStates);
    }

    public MenuState changeState(int id) {
        if(id == 0)
            return previousState;
        return availableStates.get(id - 1);
    }

}

