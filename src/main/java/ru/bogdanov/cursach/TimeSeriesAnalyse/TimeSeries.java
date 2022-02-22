package ru.bogdanov.cursach.TimeSeriesAnalyse;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TimeSeries {
    private List<LocalDate> date;
    private List<Double> ind;

    public TimeSeries() {
        this.date = new ArrayList<>();
        this.ind = new ArrayList<>();
    }
}
