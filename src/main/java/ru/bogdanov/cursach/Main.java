package ru.bogdanov.cursach;

import lombok.SneakyThrows;
import ru.bogdanov.cursach.TimeSeriesAnalyse.*;
import ru.bogdanov.cursach.generator.DataGenerator;
import ru.bogdanov.cursach.graph.Graph;
import ru.bogdanov.cursach.loader.DataLoader;

import java.io.IOException;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) throws IOException {
        DataGenerator.genTimeSeries("test.csv",true,true,10000,true,15000,2000);
        TimeSeries timeSeries1 = DataLoader.read("test.csv");
        TimeSeries timeSeries2 = Analysis.balancedCentralSmoothing(timeSeries1);
        TimeSeries timeSeries3 = new DoubleTimeSeries(timeSeries1, timeSeries2).leftMinusRight();

        List<Double> res = Compare.getCorrelationIndex(new DoubleTimeSeries(timeSeries1,timeSeries2));
        Graph.barChart(res);
        Graph.timeSeries(timeSeries1);
        Graph.fourTimeSeries(timeSeries1,timeSeries2,timeSeries2,timeSeries3);
        Graph.doubleTimeSeries(new DoubleTimeSeries(timeSeries1,timeSeries2));

        List<Pair> ss = Analysis.autoCorrelation(timeSeries3);
    //    Graph.correlogram((ArrayList<Pair>) ss);
//        List<Pair> list = Analyse.partialAutoCorelation(new DoubleTimeLine(timeLine1,timeLine2).leftMinusRight());

        if (Analysis.isSeasonSignificant(ss)) {
            TimeSeries timeSeries4 = Analysis.cutSeason(timeSeries3);
            TimeSeries timeSeries5 = new DoubleTimeSeries(timeSeries3, timeSeries4).leftMinusRight();

            Graph.fourTimeSeries(timeSeries1, timeSeries2, timeSeries4, timeSeries5);
        } else {
            Graph.tripleTimeSeries(timeSeries1, timeSeries2, timeSeries3);
        }
    //  Graph.correlogram((ArrayList<Pair>) list);


    }
}
