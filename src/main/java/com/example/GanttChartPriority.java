package com.example;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class GanttChartPriority extends JPanel {
    private final List<Process> processes;

    public GanttChartPriority(List<Process> processes) {
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
            y += h + 20; 
        }
    }

    public static void display(List<Process> processes) {
    
        JFrame frame = new JFrame("Priority Scheduling Gantt Chart");
        GanttChartPriority chart = new GanttChartPriority(processes);
        frame.add(chart);
        frame.setSize(1000, 400);
        frame.setVisible(true);
        displayReport(processes);
    }

    public static void displayReport(List<Process> processes) {
        JFrame reportFrame = new JFrame("Priority Scheduling Report");

        String[] columns = {"Process", "Color", "Priority", "Waiting Time", "Turnaround Time"};
        Object[][] data = new Object[processes.size()][5];

        int totalWT = 0, totalTAT = 0;
        for (int i = 0; i < processes.size(); i++) {
            Process p = processes.get(i);
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;

            data[i][0] = p.name;
            data[i][1] = "RGB(" + p.color.getRed() + ", " + p.color.getGreen() + ", " + p.color.getBlue() + ")";
            data[i][2] = p.priority;
            data[i][3] = p.waitingTime;
            data[i][4] = p.turnaroundTime;
        }

    
        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        reportFrame.add(scrollPane, BorderLayout.CENTER);

      
        double avgWT = (double) totalWT / processes.size();
        double avgTAT = (double) totalTAT / processes.size();
        JLabel statsLabel = new JLabel(String.format("Average Waiting Time: %.2f | Average Turnaround Time: %.2f", avgWT, avgTAT));
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        reportFrame.add(statsLabel, BorderLayout.SOUTH);

        reportFrame.setSize(600, 400);
        reportFrame.setVisible(true);
    }
}
