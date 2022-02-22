package ru.bogdanov.cursach.TimeLineAnalyse;

import lombok.experimental.UtilityClass;
import ru.bogdanov.cursach.graph.Graph;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Analyse {

    public TimeLine cutSeason(TimeLine t) {
        TimeLine t2 = new TimeLine();
        Integer p = t.getDate().size() / 50;
        Double v1 = (double) 1/p;
        for (int i = p/2 ; i < t.getDate().size() - p/2; i++) {
            Double value = 0.0;
            for (int j = 0; j < p; j++) {
                value += t.getInd().get(i + j - p/2) * v1;
            }

            t2.getInd().add(value);
            t2.getDate().add(t.getDate().get(i));

        }
        TimeLine t3 = new TimeLine();
        t3.setDate(t2.getDate());
        int d= 0;
       while (d < t2.getDate().size()) {
          t3.getInd().add(t.getInd().get(p/2 + d) - t2.getInd().get(d));
            d++;
       }
       return t3;
    }

    public boolean isSeasonSignificant(List<Pair> list) {
        Double max = (double) 0;
        Double localmax = (double) 0;
        for (int i = list.size() / 10; i < list.size(); i++) {
            if (max < list.get(i).first) {
                max = list.get(i).first;
            }
        }
        Double min = max;
        for (int i = list.size() / 10; i < list.size(); i++) {
            if (min > Math.abs(list.get(i).first)) {
                min = Math.abs(list.get(i).first);
            }
        }
        if (max > 0.7 && (max - min > 0.2))
            return true;
        else
            return false;
    }

    public Integer getSeasonPeriod(List<Pair> list) {
        Double max = (double) 0;
        int ind = -1;
        for (int i = list.size() -1 ; i > list.size() / 10; i--) {
            if (max < list.get(i).first) {
                max = list.get(i).first;
                ind = i;
            }
        }
        return ind;
    }

    public static TimeLine balancedCentralSmoothing(TimeLine t1) {
        TimeLine t2 = new TimeLine();
        Integer p = t1.getDate().size() / 10 / 100 * 100;
        Double v1 = (double) 1/p;
//        Double v2 = 0.25;
//        Double v3 = 0.4;

      //  Double[] v = new Double[] {0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1,0.1};
        for (int i = 0; i < t1.getInd().size()- p; i++) {
            Double value = 0.0;
            for (int j = 0; j < p; j++) {
                value += t1.getInd().get(i + j) * v1;
            }

            t2.getInd().add(value);
            t2.getDate().add(t1.getDate().get(i + p /2 - 1));
        }
        return t2;
    }
    public static List<Pair> partialAutoCorelation(TimeLine t1) {
        List<Pair> result = new ArrayList<>();

        for (int i = 1 ; i < t1.getDate().size() / 4; i++) {
            if (i == 1){
                result.add(new Pair(markAutoCorelation(t1,i),(double) i));
            } else {
                Double p_t = markAutoCorelation(t1,i);
                Double sum = (double) 0;
                Double sum2 = (double) 0;
                for (int j = 1; j < i; j++) {
                    sum += markAutoCorelation(t1,i-j) * result.get(i-2).first;
                    sum2 += markAutoCorelation(t1,i-j) * result.get(i-2).first;
                }
                double ch = p_t - (sum / (i-1));
                double zn = 1 - (sum2 / (i-1));
                result.add(new Pair(ch/zn, (double) i));
            }
        }
        return result;

    }

    public static List<Pair> autoCorelation(TimeLine t1) {
        List<Pair> result = new ArrayList<>();
        for (int gap = 1; gap < t1.getDate().size() / 4; gap++) {
            Double value = markAutoCorelation(t1,gap);
            result.add(new Pair(value, (double) gap));
        }
        return result;
    }

    private static Double autoCorelation(TimeLine t1, int gap) {
        Double ch = (double) 0;
        Double zn = (double) 0;
        Double zn1 = (double) 0;
        Double zn2 = (double) 0;

        Double sum = (double) 0;
        for (int i = gap ; i < t1.getInd().size(); i++) {
            double value = t1.getInd().get(i);
            sum += value;
        }
        Double x_ = sum / (t1.getInd().size() - gap);

        sum = (double) 0;
        for (int i = gap ; i < t1.getInd().size(); i++) {
            double value = t1.getInd().get(i-gap);
            sum += value;
        }
        Double y_ = sum / (t1.getInd().size() - gap);

        for (int i = gap; i < t1.getInd().size(); i++) {
            ch += (t1.getInd().get(i) - x_) * (t1.getInd().get(i - gap) - y_);
            zn1 += (t1.getInd().get(i) - x_) * (t1.getInd().get(i) - x_);
            zn2 += (t1.getInd().get(i - gap) - y_) * (t1.getInd().get(i - gap) - y_);
        }
        zn = Math.sqrt(zn1 * zn2);
        return ch / zn;
    }

    private static Double markAutoCorelation(TimeLine t1, int gap) {
        Double ch = (double) 0;
        Double zn = (double) 0;
        Double zn1 = (double) 0;
        Double zn2 = (double) 0;

        Double sum = (double) 0;
        for (int i = 0; i < t1.getInd().size(); i++) {
            double value = t1.getInd().get(i);
            sum += value;
        }
        Double x_ = sum / t1.getInd().size();
//        for (int i = gap ; i < t1.getInd().size(); i++) {
//            double value = t1.getInd().get(i);
//            sum += value;
//        }
//        Double x_ = sum / (t1.getInd().size() - gap);

        sum = (double) 0;
        for (int i = gap ; i < t1.getInd().size(); i++) {
            double value = t1.getInd().get(i-gap);
            sum += value;
        }
        Double y_ = sum / (t1.getInd().size() - gap);

        for (int i = gap; i < t1.getInd().size(); i++) {
            ch += (t1.getInd().get(i) - x_) * (t1.getInd().get(i - gap) - x_);
            zn += (t1.getInd().get(i) - x_) * (t1.getInd().get(i) - x_);
            //  zn2 += (t1.getInd().get(i - gap) - y_) * (t1.getInd().get(i - gap) - y_);
        }
        ch = ch /(t1.getInd().size() - gap);
        zn = zn / t1.getInd().size();
        //  zn = Math.sqrt(zn1 * zn2);
        return ch / zn;
    }
}

