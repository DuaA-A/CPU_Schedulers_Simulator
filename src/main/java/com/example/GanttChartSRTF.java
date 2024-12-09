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
        for (Process process : processes) {
            for (int[] interval : process.executionIntervals) {
                int pStart = interval[0];
                int pEnd = interval[1];
                int pWidth = (pEnd - pStart) * w;
                g.setColor(process.color);
                g.fillRect(x + pStart * w, y, pWidth, h);
                g.setColor(Color.BLACK);
                g.drawRect(x + pStart * w, y, pWidth, h);
                g.drawString(process.name, x + pStart * w + 4, y + h / 2);
            }
            y += h + 20; 
        }
        int maxTime = processes.stream()
                .flatMapToInt(p -> p.executionIntervals.stream().mapToInt(interval -> interval[1]))
                .max()
                .orElse(0);
        int axisY = y + 20; 
        g.setColor(Color.BLACK);
        for (int time = 0; time <= maxTime; time++) {
            int tickX = x + time * w;
            g.drawLine(tickX, axisY, tickX, axisY + 5);
            g.drawString(String.valueOf(time), tickX - 5, axisY + 20); 
            g.drawLine(tickX, 50, tickX, axisY); 
        }
        g.drawLine(x, axisY, x + maxTime * w, axisY);
        double totalWaitingTime = processes.stream().mapToInt(p -> p.waitingTime).sum();
        double totalTurnaroundTime = processes.stream().mapToInt(p -> p.turnaroundTime).sum();
        double avgWaitingTime = totalWaitingTime / processes.size();
        double avgTurnaroundTime = totalTurnaroundTime / processes.size();
        g.drawString("Average Waiting Time: " + String.format("%.2f", avgWaitingTime), x, axisY + 50);
        g.drawString("Average Turnaround Time: " + String.format("%.2f", avgTurnaroundTime), x, axisY + 70);
    }
    public static void display(List<Process> processes) {
        JFrame frame = new JFrame("SRTF Gantt Chart");
        GanttChartSRTF chart = new GanttChartSRTF(processes);
        frame.add(chart);
        frame.setSize(1000, 400);
        frame.setVisible(true);
    }
}