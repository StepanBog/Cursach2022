package ru.bogdanov.cursach;

import lombok.SneakyThrows;
import org.w3c.dom.ls.LSException;
import ru.bogdanov.cursach.TimeLineAnalyse.*;
import ru.bogdanov.cursach.generator.DataGenerator;
import ru.bogdanov.cursach.graph.Graph;
import ru.bogdanov.cursach.loader.DataLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) throws IOException {
        DataGenerator.genSin("test.csv");
        TimeLine timeLine1 = DataLoader.read("test.csv");
        TimeLine timeLine2 = Analyse.balancedCentralSmoothing(timeLine1);
        TimeLine timeLine3 = new DoubleTimeLine(timeLine1,timeLine2).leftMinusRight();
      //  Graph.timeList(timeLine1);
    //    Graph.fourTimeList(timeLine1,timeLine2,timeLine4,timeLine5);
    //    Graph.doubleTimeList(new DoubleTimeLine(timeLine1,timeLine2));

        List<Pair> ss = Analyse.autoCorelation(timeLine3);
    //    Graph.correlogram((ArrayList<Pair>) ss);
//        List<Pair> list = Analyse.partialAutoCorelation(new DoubleTimeLine(timeLine1,timeLine2).leftMinusRight());

        if (Analyse.isSeasonSignificant(ss)) {
            TimeLine timeLine4 = Analyse.cutSeason(timeLine3);
            TimeLine timeLine5 = new DoubleTimeLine(timeLine3,timeLine4).leftMinusRight();

            Graph.fourTimeList(timeLine1,timeLine2,timeLine4,timeLine5);
        } else {
            Graph.tripleTimeList(timeLine1,timeLine2,timeLine3);
        }
    //  Graph.correlogram((ArrayList<Pair>) list);


    }
}
