package ru.bogdanov.cursach.graph;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TimeLine {
    private List<LocalDate> date;
    private List<Double> ind;

    public TimeLine() {
        this.date = new ArrayList<>();
        this.ind = new ArrayList<>();
    }
}
