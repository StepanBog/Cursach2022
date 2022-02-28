package ru.bogdanov.cursach.graph;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ru.bogdanov.cursach.TimeSeriesAnalyse.DoubleTimeSeries;
import ru.bogdanov.cursach.TimeSeriesAnalyse.Pair;
import ru.bogdanov.cursach.TimeSeriesAnalyse.TimeSeries;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    public void timeSeries(TimeSeries data, String name) {
        File f = new File("timeList.plot");
        FileWriter fileWriter = new FileWriter(f);
        fileWriter.write("set xdata time\n" +
                "set encoding utf8\n" +
                "set timefmt \"%Y-%m-%d\"\n" +
                "plot 'timeList.txt' using 1:2 with lines ti \"" + name + "\"");
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
    public void doubleTimeSeries(DoubleTimeSeries data, String name, String name2) {
        File f = new File("doubleTimeList.plot");
        FileWriter fileWriter = new FileWriter(f);
        fileWriter.write("set xdata time\n" +
                "set encoding utf8\n" +
                "set timefmt \"%Y-%m-%d\"\n" +
                "plot 'doubleTimeList.txt' using 1:2 with lines ti \"" + name + "\"," +
                "'doubleTimeList.txt' using 1:3 with lines ti \"" + name2 + "\""
        );
        fileWriter.close();
        f = new File("doubleTimeList.txt");
        fileWriter = new FileWriter(f);
        for (int i = 0; i < data.getDate().size(); i++) {
            fileWriter.write(data.getDate().get(i).toString() + " ");
            fileWriter.write(data.getInd().get(i).first.toString() + " ");
            fileWriter.write(data.getInd().get(i).second.toString() + " ");
            fileWriter.write("\n");
        }

        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/doubleTimeList.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }

    @SneakyThrows
    public void tripleTimeSeries(TimeSeries data,
                               TimeSeries data1,
                               TimeSeries data2 ,String name, String name2,String name3) {
        File f = new File("tripleTimeList.plot");
        FileWriter fileWriter = new FileWriter(f);
        fileWriter.write("set xdata time\n" +
                "set encoding utf8\n" +
                "set timefmt \"%Y-%m-%d\"\n" +
                "plot 'tripleTimeList.txt' using 1:2 with lines ti \"" + name + "\"," +
                     "'tripleTimeList.txt' using 1:3 with lines ti \"" + name2 + "\"," +
                     "'tripleTimeList.txt' using 1:4 with lines ti \"" + name3 + "\","
        );
        fileWriter.close();
        f = new File("tripleTimeList.txt");
        fileWriter = new FileWriter(f);
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
    public void barChart(ArrayList<Pair> list, String name) {
        File f = new File("barChart.plot");
        FileWriter fileWriter = new FileWriter(f);
        fileWriter.write("width=1\n" +
                "set encoding utf8\n" +
                "bin(x, s) = s*int(x/s) + width/2\n" +
                "set boxwidth width\n" +
                "set grid\n" +
                "plot 'barChart.txt' u ($1):($2) \\\n" +
                "s f w boxes fs solid 0.5 title '" + name + "'");
        fileWriter.close();
        f = new File("barChart.txt");
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).second.toString() + " " +
                    list.get(i).first.toString() +
                    "\n";
            Files.write(Paths.get("barChart.txt"), str.getBytes(), StandardOpenOption.APPEND);
        }


        Runtime commandPrompt = Runtime.getRuntime();
        String command = "gnuplot -persist " + Paths.get("").toAbsolutePath().toString().replace("\\", "/")+ "/barChart.plot";
        commandPrompt.exec(command).waitFor(10L, TimeUnit.MINUTES);
    }
}
