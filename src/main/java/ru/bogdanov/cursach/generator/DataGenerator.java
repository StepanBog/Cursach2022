package ru.bogdanov.cursach.generator;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;

@UtilityClass
public class DataGenerator {

    @SneakyThrows
    public void genTimeSeries(String name,
                       boolean trend,
                       boolean season, int seasonValue,
                       boolean random, int randomValue,
                       int len) {
        File f = new File(name);
        FileWriter fw = new FileWriter(f);
        fw.write("<DATE>,<VAL>\n");
        LocalDate date = LocalDate.now();
        for (int i = 0; i < len; i++) {
            if (i != 0)
                fw.write("\n");
            double v = 0;
            if (trend)
                v += Math.sqrt(i) * i;
            if (season)
                v += Math.sin((float) i / 30) * seasonValue;
            if (random)
                v += (Math.random() - 0.5) * randomValue;
            fw.write(date.getYear() + ""
                    + new DecimalFormat("00").format(date.getMonthValue()) + ""
                    + new DecimalFormat("00").format(date.getDayOfMonth()) + ""
                    + v);
            date = date.plusDays(1);
        }
        fw.close();
    }
    @SneakyThrows
    public void genCos(String name) {
        File f = new File(name);
        FileWriter fw = new FileWriter(f);
        fw.write("<DATE>,<TIME>,<OPEN>,<HIGH>,<LOW>,<CLOSE>,<VOL>\n");
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 1000; i++) {
            if (i != 0)
                fw.write("\n");
            double v = Math.sqrt(i) * i + Math.cos((float) i / 30) * 10000 + (Math.random() - 0.5) * 6000;
            fw.write(date.getYear() + "" + new DecimalFormat("00").format(date.getMonthValue()) + "" + new DecimalFormat("00").format(date.getDayOfMonth()) + ""
                    + ",000000,"
                    + v
                    + ","
                    + v
                    + ","
                    + v
                    + ","
                    + v
                    + ","
                    + "1");
            date = date.plusDays(1);
        }
        fw.close();
    }
}
