package ru.bogdanov.cursach.TimeSeriesAnalyse;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class Analysis {

    public TimeSeries cutSeason(TimeSeries timeSeries) {
        TimeSeries t2 = new TimeSeries();
        Integer p = timeSeries.getDate().size() / 50;
        Double v1 = (double) 1 / p;
        for (int i = p / 2; i < timeSeries.getDate().size() - p / 2; i++) {
            Double value = 0.0;
            for (int j = 0; j < p; j++) {
                value += timeSeries.getInd().get(i + j - p / 2) * v1;
            }

            t2.getInd().add(value);
            t2.getDate().add(timeSeries.getDate().get(i));

        }
        TimeSeries t3 = new TimeSeries();
        t3.setDate(t2.getDate());
        int d = 0;
        while (d < t2.getDate().size()) {
            t3.getInd().add(timeSeries.getInd().get(p / 2 + d) - t2.getInd().get(d));
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

    public static TimeSeries balancedCentralSmoothing(TimeSeries t1) {
        TimeSeries t2 = new TimeSeries();
        Integer p = t1.getDate().size() / 10 / 100 * 100;
        Double v1 = (double) 1 / p;
        for (int i = 0; i < t1.getInd().size() - p; i++) {
            Double value = 0.0;
            for (int j = 0; j < p; j++) {
                value += t1.getInd().get(i + j) * v1;
            }

            t2.getInd().add(value);
            t2.getDate().add(t1.getDate().get(i + p / 2 - 1));
        }
        return t2;
    }

    public static List<Pair> autoCorrelation(TimeSeries t1) {
        List<Pair> result = new ArrayList<>();
        for (int gap = 1; gap < t1.getDate().size() / 4; gap++) {
            Double value = markAutoCorrelation(t1, gap);
            result.add(new Pair(value, (double) gap));
        }
        return result;
    }

    private static Double markAutoCorrelation(TimeSeries t1, int gap) {
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
        for (int i = gap; i < t1.getInd().size(); i++) {
            double value = t1.getInd().get(i - gap);
            sum += value;
        }
        Double y_ = sum / (t1.getInd().size() - gap);

        for (int i = gap; i < t1.getInd().size(); i++) {
            ch += (t1.getInd().get(i) - x_) * (t1.getInd().get(i - gap) - x_);
            zn += (t1.getInd().get(i) - x_) * (t1.getInd().get(i) - x_);
            //  zn2 += (t1.getInd().get(i - gap) - y_) * (t1.getInd().get(i - gap) - y_);
        }
        ch = ch / (t1.getInd().size() - gap);
        zn = zn / t1.getInd().size();
        //  zn = Math.sqrt(zn1 * zn2);
        return ch / zn;
    }
}

