package com.company;

import com.company.models.Constants;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Helper {

    public static int readInt() {
        return new Scanner(System.in).nextInt();
    }

    public static String readString() {
        return new Scanner(System.in).nextLine();
    }

    public static LocalDate parseLocalDate(String dateString) {
        return parseDate(dateString, Constants.DATE_PATTERN)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
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

    public static void printValue(String valueName){
        printValue(valueName, "");
    }

    public static void printValue(String valueName, String value) {
        System.out.println(valueName + ": " + value);
    }

    public static void writeToFile(String filePath, String data){
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8)
                .forEach(sb::append);
        return sb.toString();
    }
}
