package ru.bogdanov.cursach;

import ru.bogdanov.cursach.graph.Graph;
import ru.bogdanov.cursach.graph.TimeLine;
import ru.bogdanov.cursach.loader.DataLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        TimeLine timeLine = loader.read("data.csv");

        EventQueue.invokeLater(() -> {
            Graph ex = new Graph(timeLine);
            ex.setVisible(true);
        });
    }
}
