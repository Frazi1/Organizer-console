package com.company;

import com.company.controllers.ConsoleHospitalController;

import java.util.ArrayList;
import java.util.List;

import static com.company.Helper.printList;
import static com.company.Helper.readInt;

public class Main {

    private final static String filePath = "events.json";

    public static void main(String[] args) {

        final List<String> menuList = new ArrayList<>();
        init(menuList);

        while(true){
            printList(menuList);
            int commandNumber = readInt();
            switch (commandNumber) {
                case 1: {
                    ConsoleHospitalController.getInstance().addHospital();
                    break;
                }
                case 2: {
                    ConsoleHospitalController.getInstance().addPatient();
                    break;
                }
                case 3: {
                    ConsoleHospitalController.getInstance().removePatient();
                    break;
                }
                case 4: {
                    ConsoleHospitalController.getInstance().removeHospital();
                    break;
                }
                case 5: {
                    ConsoleHospitalController.getInstance().printPatients();
                    break;
                }
                case 6: {
                    ConsoleHospitalController.getInstance().printHospitals();
                    break;
                }
                case 7: {
                    ConsoleHospitalController.getInstance().saveChanges();
                    break;
                }
                case 0: {
                    System.exit(0);
                }
            }
        }
    }

    private static void init(List<String> menuList) {
        menuList.add("1. Add hospital");
        menuList.add("2. Add patient");
        menuList.add("3. Remove patient");
        menuList.add("4. Remove hospital");
        menuList.add("5. Show patients");
        menuList.add("6. Show hospitals");
        menuList.add("7. Save to file");

    }
}
