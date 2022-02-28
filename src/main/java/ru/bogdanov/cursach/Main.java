package ru.bogdanov.cursach;

import lombok.SneakyThrows;
import ru.bogdanov.cursach.TimeSeriesAnalyse.*;
import ru.bogdanov.cursach.generator.DataGenerator;
import ru.bogdanov.cursach.graph.Graph;
import ru.bogdanov.cursach.loader.DataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) throws IOException {
    //    DataGenerator.genTimeSeries("test.csv", true, true, 10000, true, 15000, 2000);
   //     DataGenerator.genTimeSeriesCos("test1.csv", false, true, 10000, true, 15000, 2000);
        TimeSeries timeSeries1 = DataLoader.read("test.csv");
     //  TimeSeries timeSeries2 = DataLoader.read("test1.csv");
        TimeSeries timeSeries2 = Analysis.balancedCentralSmoothing(timeSeries1);
        // List<Pair> d= Compare.getCorrelationIndex(new DoubleTimeSeries(timeSeries2,timeSeries1));
   //     List<Pair> d = Compare.getCorrelationIndex(new DoubleTimeSeries(timeSeries2,new DoubleTimeSeries(timeSeries1,timeSeries3).leftMinusRight()));
          Graph.timeSeries(timeSeries1,"Среднесуточная температура");
      //  List<Pair> ss1 = Analysis.autoCorrelation(timeSeries1);
  //      Graph.doubleTimeSeries(new DoubleTimeSeries(timeSeries1,timeSeries2),"Ряд с sin","Ряд с cos");
      //    Graph.barChart((ArrayList<Pair>) d,"Корреляция");
       // TimeSeries timeSeries2 = Analysis.balancedCentralSmoothing(timeSeries1);
        //   Graph.timeSeries(timeSeries1, "Временной ряд");
        TimeSeries timeSeries3 = new DoubleTimeSeries(timeSeries1, timeSeries2).leftMinusRight();
      //  Graph.tripleTimeSeries(new DoubleTimeSeries(timeSeries1, timeSeries2).getLeftTimeLine(), new DoubleTimeSeries(timeSeries1, timeSeries2).getRightTimeLine(), timeSeries3, "Исходный временнной ряд", "Тренд", "Временной ряд без тренда");
        List<Pair> ss = Analysis.autoCorrelation(timeSeries3);
        Graph.barChart((ArrayList<Pair>) ss, "Автокорреляция без тренда");
        if (Analysis.isSeasonSignificant(ss)) {
            TimeSeries timeSeries4 = Analysis.cutSeason(timeSeries3);
            TimeSeries timeSeries5 = new DoubleTimeSeries(timeSeries3, timeSeries4).leftMinusRight();
            Graph.tripleTimeSeries(timeSeries4, timeSeries2, timeSeries5, "Шум", "Тренд", "Сезон");
    //   //     Graph.doubleTimeSeries(new DoubleTimeSeries(timeSeries1, new DoubleTimeSeries(new DoubleTimeSeries(timeSeries4, timeSeries5).sum(), timeSeries2).sum()), "Сумма компонент ряда", "Исходный временной ряд");
        }
    }
}
