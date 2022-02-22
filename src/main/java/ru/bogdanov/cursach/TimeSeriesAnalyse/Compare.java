package ru.bogdanov.cursach.TimeSeriesAnalyse;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Compare {

    public List<Double> getCorrelationIndex(DoubleTimeSeries timeSeries) {
        List<Double> result = new ArrayList<>();
        for (int gap = 0; gap < timeSeries.getDate().size() / 4; gap++) {
            Integer len = timeSeries.getDate().size();
            Double ch = (double) 0;
            Double zn = (double) 0;
            Double zn1 = (double) 0;
            Double zn2 = (double) 0;
            TimeSeries t1 = timeSeries.getLeftTimeLine();
            TimeSeries t2 = timeSeries.getRightTimeLine();
            Double x_ = getMiddle(t1,0,t1.getDate().size() - gap);
            Double y_ = getMiddle(t2,gap,timeSeries.getDate().size());
            for (int i = 0; i < len - gap; i++) {
                ch += (t1.getInd().get(i) - x_) * (t2.getInd().get(i + gap) - y_);
                zn1 += (t1.getInd().get(i) - x_) * (t1.getInd().get(i) - x_);
                zn2 += (t2.getInd().get(i + gap) - y_) * (t2.getInd().get(i + gap) - y_);
            }
            zn = Math.sqrt(zn1 * zn2);
            result.add(ch / zn);
        }
        return result;

    }

    private Double getMiddle(TimeSeries timeSeries,
                             Integer beginIndex, Integer
                                     endIndex){
        beginIndex++;
        endIndex++;
        Double sum = (double) 0;
        for (Double value: timeSeries.getInd()) {
            sum += value;
        }
        return sum / timeSeries.getInd().size();
    }
}
