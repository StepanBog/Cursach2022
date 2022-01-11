package ru.bogdanov.cursach.graph;

import lombok.Data;
import lombok.RequiredArgsConstructor;
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
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

@Data
public class Graph extends JFrame{
    public Graph(TimeLine timeLine){
        initUI(timeLine);
    }

    public TimeSeriesCollection createDataSet(TimeLine timeLine) {
        TimeSeries s1 = new TimeSeries("График №1");
        for (int i = 1300; i < timeLine.getDate().size(); i++) {
            s1.add(new Day(timeLine.getDate().get(i).getDayOfMonth(),
                           timeLine.getDate().get(i).getMonthValue(),
                           timeLine.getDate().get(i).getYear()),
                    timeLine.getInd().get(i));
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
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
}
