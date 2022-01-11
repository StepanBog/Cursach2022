package ru.bogdanov.cursach.loader;

import java.time.LocalDate;

public class Parser {
    public static LocalDate parseDate(String date) {
        LocalDate date1 = LocalDate.of(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6,8)));
        return date1;
    }
}
