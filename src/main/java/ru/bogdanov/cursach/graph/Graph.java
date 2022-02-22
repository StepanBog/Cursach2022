package ru.bogdanov.cursach.graph;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.bogdanov.cursach.TimeLineAnalyse.DoubleTimeLine;
import ru.bogdanov.cursach.TimeLineAnalyse.Pair;
import ru.bogdanov.cursach.TimeLineAnalyse.TimeLine;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class Graph {
    @SneakyThrows
    public void correlogram(List<Double> data) {
        File f = new File("correlogram.txt");
        FileWriter fileWriter = new FileWriter(f);
        int i = 1;
        for (Double d: data) {
            fileWriter.write(i + " ");
            fileWriter.write(d.toString());
            fileWriter.write("\n");
            i++;
        }
        fileWriter.close();

        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/correlogram.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }

    @SneakyThrows
    public void timeList(TimeLine data) {
        File f = new File("timeList.txt");
        FileWriter fileWriter = new FileWriter(f);
        for (int i = 0; i < data.getDate().size(); i++) {
            fileWriter.write(data.getDate().get(i).toString() + " ");
            fileWriter.write(data.getInd().get(i).toString());
            fileWriter.write("\n");
        }

        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/timeList.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }

    @SneakyThrows
    public void doubleTimeList(DoubleTimeLine data) {
        File f = new File("doubleTimeList.txt");
        FileWriter fileWriter = new FileWriter(f);
        for (int i = 0; i < data.getDate().size(); i++) {
            fileWriter.write(data.getDate().get(i).toString() + " ");
            fileWriter.write(data.getInd().get(i).first.toString() + " ");
            fileWriter.write(data.getInd().get(i).second.toString());
            fileWriter.write("\n");
        }

        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/doubleTimeList.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }

    @SneakyThrows
    public void tripleTimeList(TimeLine data,TimeLine data1, TimeLine data2) {
        File f = new File("tripleTimeList.txt");
        FileWriter fileWriter = new FileWriter(f);
        for (int i = 0; i < data2.getDate().size(); i++) {
            fileWriter.write(data.getDate().get(i).toString() + " ");
            fileWriter.write(data.getInd().get(i).toString() + " ");
            fileWriter.write(data1.getInd().get(i).toString() + " ");
            fileWriter.write(data2.getInd().get(i).toString());
            fileWriter.write("\n");
        }

        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/tripleTimeList.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }
    @SneakyThrows
    public void fourTimeList(TimeLine data,TimeLine data1, TimeLine data2, TimeLine data3) {
        File f = new File("fourTimeList.txt");
        FileWriter fileWriter = new FileWriter(f);
        for (int i = 0; i < data2.getDate().size(); i++) {
            fileWriter.write(data.getDate().get(i).toString() + " ");
            fileWriter.write(data.getInd().get(i).toString() + " ");
            fileWriter.write(data1.getInd().get(i).toString() + " ");
            fileWriter.write(data3.getInd().get(i).toString() + " ");
            fileWriter.write(data2.getInd().get(i).toString());
            fileWriter.write("\n");
        }

        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/fourTimeList.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }

    @SneakyThrows
    public void correlogram(ArrayList<Pair> list) {
        File f = new File("correlogram2.txt");
        FileWriter fileWriter = new FileWriter(f);
        int i = 1;
        for (Pair d: list) {
            fileWriter.write(d.second + " ");
            fileWriter.write(d.first.toString());
            fileWriter.write("\n");
            i++;
        }
        fileWriter.close();

        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/correlogram2.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }
}
