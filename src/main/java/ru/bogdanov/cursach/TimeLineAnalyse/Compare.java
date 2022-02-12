package ru.bogdanov.cursach.TimeLineAnalyse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Compare {

    public Double getСorrelationIndex(DoubleTimeLine timeLine, Integer beginIndex,Integer endIndex) {
        Integer len = endIndex- beginIndex;
        Double ch = (double) 0;
        Double zn = (double) 0;
        Double zn1 = (double) 0;
        Double zn2 = (double) 0;
        TimeLine t1 = timeLine.getLeftTimeLine();
        TimeLine t2 = timeLine.getRightTimeLine();
        Double x_ = getMiddle(t1,beginIndex,endIndex);
        Double y_ = getMiddle(t2,beginIndex,endIndex);
        for (int i = 0; i < len; i++) {
            ch += (t1.getInd().get(i) - x_) * (t2.getInd().get(i) - y_);
            zn1 += (t1.getInd().get(i) - x_) * (t1.getInd().get(i) - x_);
            zn2 += (t2.getInd().get(i) - y_) * (t2.getInd().get(i) - y_);
        }
        zn = Math.sqrt(zn1 * zn2);
        return ch / zn;
    }

    public Double getСorrelationIndex(DoubleTimeLine timeLine) {
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
