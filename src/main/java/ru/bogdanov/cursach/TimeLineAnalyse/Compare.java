package ru.bogdanov.cursach.TimeLineAnalyse;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Compare {

    public List<Double> getСorrelationIndex(DoubleTimeLine timeLine, Integer beginIndex,Integer endIndex) {
        List<Double> result = new ArrayList<>();
        for (int gap = 0; gap < timeLine.getDate().size() / 4; gap++) {
            Integer len = endIndex- beginIndex;
            Double ch = (double) 0;
            Double zn = (double) 0;
            Double zn1 = (double) 0;
            Double zn2 = (double) 0;
            TimeLine t1 = timeLine.getLeftTimeLine();
            TimeLine t2 = timeLine.getRightTimeLine();
            Double x_ = getMiddle(t1,beginIndex,endIndex);
            Double y_ = getMiddle(t2,beginIndex,endIndex);
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

    public List<Double> getСorrelationIndex(DoubleTimeLine timeLine) {
        return getСorrelationIndex(timeLine,0,timeLine.getDate().size());
    }



    private Double getMiddle(TimeLine timeLine,Integer beginIndex,Integer endIndex){
        Double sum = (double) 0;
        for (Double value: timeLine.getInd()) {
            sum += value;
        }
        return sum / timeLine.getInd().size();
    }
}
