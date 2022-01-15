package ru.bogdanov.cursach.TimeLineAnalyse;

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
    public TimeLine stretchingByDay(TimeLine t1) {
        TimeLine newTimeLine = new TimeLine();
        boolean flag = true;
        int dateX = 0;
        int dateY = 0;
//        if (!this.getDate().get(dateX).equals(t1.getDate().get(dateY))) {
//
//        }
//        while (flag) {
//            if (this.getDate().get(dateX))
//        }
        return  newTimeLine;
    }

    public TimeLine minus(TimeLine t1) {
        return minus(t1,0);
    }
    public TimeLine minus(TimeLine t1,int p) {
        TimeLine timeLine = new TimeLine();
        timeLine.setDate(t1.getDate());
        int p_2 = Math.floorDiv(p,2);
        for (int i = p_2 - 1; i < this.getDate().size() - p_2 - 1; i++) {
            timeLine.getInd().add(this.getInd().get(i) - t1.getInd().get(i - p_2 + 1));
        }
        return timeLine;
    }
}
