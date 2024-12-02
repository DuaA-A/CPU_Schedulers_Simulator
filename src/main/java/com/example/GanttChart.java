package com.example;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GanttChart extends JPanel {
    private final List<Process> processes;

    public GanttChart(List<Process> processes) {
        this.processes = processes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 50;
        int h = 30; 
        int w = 20; 
        for (Process process : processes) {
            for (int[] interval : process.executionIntervals) {
                int pX = interval[0];
                int pEnd = interval[1];
                int pWidth = (pEnd - pX) * w;
                g.setColor(process.color);
                g.fillRect(x + pX * w, y, pWidth, h);
                g.setColor(Color.BLACK);
                g.drawString(process.name, x + pX * w + 5, y + w);
                g.drawRect(x + pX * w, y, pWidth, h);
            }
        }
    }

    public static void display(List<Process> processes) {
        JFrame frame = new JFrame("SRTF Gantt Chart");
        GanttChart chart = new GanttChart(processes);
        frame.add(chart);
        frame.setSize(800, 200);
        frame.setVisible(true);
    }
}
