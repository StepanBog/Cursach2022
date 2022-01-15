package ru.bogdanov.cursach.graph;

import lombok.Data;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import ru.bogdanov.cursach.TimeLineAnalyse.TimeLine;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

@Data
public class Graph extends JFrame{
    public Graph(TimeLine timeLine){
        initUI(timeLine);
    }
    public Graph(TimeLine timeLine1,TimeLine timeLine2){
        initUI(timeLine1,timeLine2);
    }

    public TimeSeriesCollection createDataSet(TimeLine timeLine) {
        TimeSeries s1 = new TimeSeries("График №1");
        for (int i = 0; i < timeLine.getDate().size(); i++) {
            s1.add(new Day(timeLine.getDate().get(i).getDayOfMonth(),
                           timeLine.getDate().get(i).getMonthValue(),
                           timeLine.getDate().get(i).getYear()),
                    timeLine.getInd().get(i));
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }
    public TimeSeriesCollection createDataSet(TimeLine timeLine1,TimeLine timeLine2) {
        TimeSeries s1 = new TimeSeries("График №1");
        TimeSeries s2 = new TimeSeries("График №2");
        for (int i = 0; i < timeLine1.getDate().size(); i++) {
            s1.add(new Day(timeLine1.getDate().get(i).getDayOfMonth(),
                            timeLine1.getDate().get(i).getMonthValue(),
                            timeLine1.getDate().get(i).getYear()),
                    timeLine1.getInd().get(i));
        }
        for (int i = 0; i < timeLine2.getDate().size(); i++) {
            s2.add(new Day(timeLine2.getDate().get(i).getDayOfMonth(),
                            timeLine2.getDate().get(i).getMonthValue(),
                            timeLine2.getDate().get(i).getYear()),
                    timeLine2.getInd().get(i));
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }
    public JFreeChart createChart(XYDataset dataset){
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Индексы МосБиржи",  // title
                "Дата",                            // x-axis label
                "Индекс",                      // y-axis label
                dataset,                       // data
                true,                          // create legend
                true,                          // generate tooltips
                false);

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint    (Color.lightGray);
        plot.setDomainGridlinePaint(Color.white    );
        plot.setRangeGridlinePaint (Color.white    );
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible   (true);
            renderer.setBaseShapesFilled    (true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("YYYY/MM/DD"));
        return chart;
    }
    public void initUI(TimeLine timeLine) {

        XYDataset dataset = createDataSet(timeLine);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 3000));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Индексы МосБиржи");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void initUI(TimeLine timeLine1,TimeLine timeLine2) {

        XYDataset dataset = createDataSet(timeLine1,timeLine2);
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 3000));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Индексы МосБиржи");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
