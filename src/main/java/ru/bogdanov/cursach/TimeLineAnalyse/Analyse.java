package ru.bogdanov.cursach.TimeLineAnalyse;

public class Analyse {

    public static TimeLine balancedCentralSmoothing(TimeLine t1) {
        TimeLine t2 = new TimeLine();
        Double v1 = 0.01;
//        Double v2 = 0.25;
//        Double v3 = 0.4;
        Integer p = 100;
      //  Double[] v = new Double[] {0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1};
        for (int i = 0; i < t1.getInd().size()- p; i++) {
            Double value = 0.0;
            for (int j = 0; j < p; j++) {
                value += t1.getInd().get(i + j) * v1;
            }

            t2.getInd().add(value);
            t2.getDate().add(t1.getDate().get(i + 49));
        }
        return t2;
    }
}
