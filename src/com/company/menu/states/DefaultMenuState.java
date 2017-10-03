package com.company.menu.states;

import com.company.menu.Menu;
import com.company.repositories.EventRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultMenuState extends MenuState {

    public DefaultMenuState(MenuState previousState) {
        super(previousState);
        addState(new EventsAddState(this));
    }

    @Override
    public void run(EventRepository eventRepository) {
    }
}
