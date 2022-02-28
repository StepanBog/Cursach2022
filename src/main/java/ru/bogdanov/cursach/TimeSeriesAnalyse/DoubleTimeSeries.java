package ru.bogdanov.cursach.TimeSeriesAnalyse;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DoubleTimeSeries {
    private List<LocalDate> date = new ArrayList<>();
    private List<Pair> ind = new ArrayList<>();

    public DoubleTimeSeries(TimeSeries t1, TimeSeries t2) {
        LocalDate firstDate;
        LocalDate lastDate;
        if (t1.getDate().get(0).isBefore(t2.getDate().get(0)))
            firstDate = t1.getDate().get(0);
        else
            firstDate = t2.getDate().get(0);
        if (t1.getDate().get(t1.getDate().size() - 1).isAfter(t2.getDate().get(t2.getDate().size() - 1)))
            lastDate = t1.getDate().get(t1.getDate().size() - 1);
        else
            lastDate = t2.getDate().get(t2.getDate().size() - 1);

        LocalDate currentDate = firstDate;
        while (currentDate.isBefore(lastDate)) {
            if (t1.getDate().contains(currentDate) && t2.getDate().contains(currentDate)) {
                int index1 = t1.getDate().indexOf(currentDate);
                int index2 = t2.getDate().indexOf(currentDate);
                date.add(currentDate);
                ind.add(new Pair(t1.getInd().get(index1), t2.getInd().get(index2)));
            }
            currentDate = currentDate.plusDays(1);
        }
    }

    public TimeSeries getLeftTimeLine() {
        TimeSeries timeSeries = new TimeSeries();
        for (int i = 0; i < date.size(); i++) {
            timeSeries.getDate().add(date.get(i));
            timeSeries.getInd().add(ind.get(i).first);
        }
        return timeSeries;
    }

    public TimeSeries getRightTimeLine() {
        TimeSeries timeSeries = new TimeSeries();
        for (int i = 0; i < date.size(); i++) {
            timeSeries.getDate().add(date.get(i));
            timeSeries.getInd().add(ind.get(i).second);
        }
        return timeSeries;
    }

    public TimeSeries leftMinusRight() {
        TimeSeries result = new TimeSeries();
        result.setDate(date);
        ind.forEach(ind -> result.getInd().add(ind.first - ind.second));
        return result;
    }

    public TimeSeries sum() {
        TimeSeries result = new TimeSeries();
        result.setDate(date);
        ind.forEach(ind -> result.getInd().add(ind.first + ind.second));
        return result;
    }
}
