package com.example;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GanttChartSRTF extends JPanel {
    private final List<Process> processes;

    public GanttChartSRTF(List<Process> processes) {
        this.processes = processes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50, y = 50, h = 30, w = 20; 
        int currentTime = 0; 
        
        for (Process process : processes) {
            for (int[] interval : process.executionIntervals) {
                int pStart = interval[0];
                int pEnd = interval[1];
                int pWidth = (pEnd - pStart) * w;
                g.setColor(process.color);
                g.fillRect(x + pStart * w, y, pWidth , h);
                g.drawRect(x + pStart * w, y, pWidth , h);
                g.setColor(Color.BLACK);
                g.drawString(process.name, x + pStart * w + 4, y + h / 2);
                currentTime = pEnd;
            }
            y += h + 20;
        }
    }

    public static void display(List<Process> processes) {
        JFrame frame = new JFrame("SRTF Gantt Chart");
        GanttChartSRTF chart = new GanttChartSRTF(processes);
        frame.add(chart);
        frame.setSize(1000, 400);
        frame.setVisible(true);
    }
}
