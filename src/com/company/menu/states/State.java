package com.company.menu.states;

import java.util.List;

public abstract class State<T> {
    public abstract void run(T param);
}
