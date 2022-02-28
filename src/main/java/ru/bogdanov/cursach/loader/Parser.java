package ru.bogdanov.cursach.loader;

import java.time.LocalDate;

public class Parser {
    public static LocalDate parseDate(String date) {
        LocalDate date1 = LocalDate.of(Integer.parseInt(date.substring(6,10)),Integer.parseInt(date.substring(3,5)),Integer.parseInt(date.substring(0,2)));
        return date1;
    }
}
