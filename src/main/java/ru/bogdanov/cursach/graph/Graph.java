package ru.bogdanov.cursach.graph;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.bogdanov.cursach.TimeSeriesAnalyse.DoubleTimeSeries;
import ru.bogdanov.cursach.TimeSeriesAnalyse.Pair;
import ru.bogdanov.cursach.TimeSeriesAnalyse.TimeSeries;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class Graph {
    @SneakyThrows
    public void barChart(List<Double> data) {
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
    public void timeSeries(TimeSeries data) {
        File f = new File("timeList.plot");
        FileWriter fileWriter = new FileWriter(f);
        fileWriter.write("set xdata time\n" +
                "set timefmt \"%Y-%m-%d\"\n" +
                "plot 'timeList.txt' using 1:2 with lines");
        fileWriter.close();
        f = new File("timeList.txt");
        fileWriter = new FileWriter(f);
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
    public void doubleTimeSeries(DoubleTimeSeries data) {
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
    public void tripleTimeSeries(TimeSeries data,
                               TimeSeries data1,
                               TimeSeries data2) {
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
    public void fourTimeSeries(TimeSeries data,
                             TimeSeries data1,
                             TimeSeries data2,
                             TimeSeries data3) {
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
    public void barChart(ArrayList<Pair> list) {
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
