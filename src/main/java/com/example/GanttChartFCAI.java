package com.example;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GanttChartFCAI extends JPanel {
    private final List<Process> processes;

    public GanttChartFCAI(List<Process> processes) {
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
                int startX = interval[0];
                int endX = interval[1];
                int pWidth = (endX - startX) * w;

                g.setColor(process.color);
                g.fillRect(x + startX * w, y, pWidth, h);

                g.setColor(Color.BLACK);
                g.drawRect(x + startX * w, y, pWidth, h);
                g.drawString(process.name, x + startX * w + 5, y + h - 10);

                String intervalText = "[" + startX + ", " + endX + "]";
                g.drawString(intervalText, x + startX * w + 5, y + h + 15);
            }
            y += h + 40;
        }
    }

    public static void display(List<Process> processes) {
        JFrame frame = new JFrame("FCAI Scheduling Gantt Chart");
        GanttChartFCAI chart = new GanttChartFCAI(processes);
        frame.add(chart);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}
