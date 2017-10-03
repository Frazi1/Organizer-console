package com.company.menu;

import com.company.menu.states.DefaultMenuState;
import com.company.menu.states.MenuState;
import com.company.repositories.EventRepository;

public class Menu {
    private MenuState currentState;
    private EventRepository eventRepository;

    public Menu(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        currentState = new DefaultMenuState(null);
    }

    public void run() {
        currentState.run(eventRepository);
        currentState.printMenu();
        changeState();
    }

    private void changeState(){
        currentState = currentState.changeState(currentState.readInteger());
        run();
    }
}
