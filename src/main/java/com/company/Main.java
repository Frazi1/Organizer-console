package com.company;

import com.company.controllers.ConsoleEventsController;

import java.util.ArrayList;
import java.util.List;

import static com.company.Helper.printList;
import static com.company.Helper.readInt;

public class Main {

    private final static String filePath = "events.json";

    public static void main(String[] args) {
        ConsoleEventsController.setFilePath(filePath);
        final List<String> menuList = new ArrayList<>();
        init(menuList);

        while(true){
            printList(menuList);
            int commandNumber = readInt();
            switch (commandNumber) {
                case 1: {
                    ConsoleEventsController.getInstance().addEvent();
                    break;
                }
                case 2: {
                    ConsoleEventsController.getInstance().printEvents();
                    break;
                }
                case 3: {
                    ConsoleEventsController.getInstance().saveChanges();
                }
            }
        }
    }

    private static void init(List<String> menuList) {
        menuList.add("1. Add event");
        menuList.add("2. Show events");
        menuList.add("3. Save to file");
    }
}
