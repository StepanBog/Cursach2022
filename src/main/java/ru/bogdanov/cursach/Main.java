package ru.bogdanov.cursach;

import ru.bogdanov.cursach.TimeLineAnalyse.*;
import ru.bogdanov.cursach.generator.DataGenerator;
import ru.bogdanov.cursach.graph.Graph;
import ru.bogdanov.cursach.loader.DataLoader;

import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataGenerator.gen("test.csv");
        DataLoader loader = new DataLoader();
        TimeLine timeLine1 = loader.read("test.csv");
          TimeLine timeLine2 = Analyse.balancedCentralSmoothing(timeLine1);
//        EventQueue.invokeLater(() -> {
//            Graph ex = new Graph(timeLine1);
//            ex.setVisible(true);
//        });
        EventQueue.invokeLater(() -> {
            Graph ex = new Graph(timeLine1,new DoubleTimeLine(timeLine1,timeLine2).leftMinusRight());
            ex.setVisible(true);
        });
      //  System.out.println(Compare.getСorrelationIndex(new DoubleTimeLine(timeLine1,timeLine2)));
        List<Pair> list = Analyse.partialAutoCorelation(new DoubleTimeLine(timeLine1,timeLine2).leftMinusRight());
        list.forEach(pair -> {
            if (Math.abs(pair.first) > 0.6)
            System.out.println(pair.first + " " + pair.second);
        });
        System.out.println();
    }
}
