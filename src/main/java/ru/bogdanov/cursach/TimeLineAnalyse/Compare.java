package ru.bogdanov.cursach.TimeLineAnalyse;

public class Compare {

    public Double getСorrelationIndex(TimeLine t1,TimeLine t2,Integer beginIndex,Integer endIndex) {
        Integer len = endIndex- beginIndex;
        Double ch = (double) 0;
        Double zn = (double) 0;
        Double zn1 = (double) 0;
        Double zn2 = (double) 0;
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

    public Double getСorrelationIndex(TimeLine t1,TimeLine t2) {
        return getСorrelationIndex(t1,t2,0,t1.getDate().size());
    }



    private Double getMiddle(TimeLine timeLine,Integer beginIndex,Integer endIndex){
        Double sum = (double) 0;
        for (Double value: timeLine.getInd()) {
            sum += value;
        }
        return sum / timeLine.getInd().size();
    }
}
