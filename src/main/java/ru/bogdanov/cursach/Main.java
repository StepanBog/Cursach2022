package ru.bogdanov.cursach;

import ru.bogdanov.cursach.TimeLineAnalyse.Analyse;
import ru.bogdanov.cursach.TimeLineAnalyse.Compare;
import ru.bogdanov.cursach.graph.Graph;
import ru.bogdanov.cursach.TimeLineAnalyse.TimeLine;
import ru.bogdanov.cursach.loader.DataLoader;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        TimeLine timeLine1 = loader.read("EURSEK_160113_220113.csv");
          TimeLine timeLine2 = Analyse.balancedCentralSmoothing(timeLine1);
//        EventQueue.invokeLater(() -> {
//            Graph ex = new Graph(timeLine1);
//            ex.setVisible(true);
//        });
        EventQueue.invokeLater(() -> {
            Graph ex = new Graph(timeLine1.minus(timeLine2,100));
            ex.setVisible(true);
        });
   //     Compare compare = new Compare();
  //      System.out.println(compare.getСorrelationIndex(timeLine1,timeLine2));
    }
}
