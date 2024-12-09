package com.example;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    int remainingTime;
    int lastExecutionTime;
    Color color;
    List<int[]> executionIntervals = new ArrayList<>();
    double fcaiFactor;
    boolean processed;
    int completionT =0;
    int QT;

    private static final Map<String, Color> predefinedColors = new HashMap<>();
    static {
        predefinedColors.put("red", Color.RED);
        predefinedColors.put("blue", Color.BLUE);
        predefinedColors.put("green", Color.GREEN);
        predefinedColors.put("yellow", Color.YELLOW);
        predefinedColors.put("cyan", Color.CYAN);
        predefinedColors.put("magenta", Color.MAGENTA);
        predefinedColors.put("orange", Color.ORANGE);
        predefinedColors.put("pink", Color.PINK);
        predefinedColors.put("gray", Color.GRAY);
        predefinedColors.put("black", Color.BLACK);
        predefinedColors.put("purple", new Color(128, 0, 128));
        predefinedColors.put("brown", new Color(165, 42, 42));
        predefinedColors.put("light_gray", Color.LIGHT_GRAY);
        predefinedColors.put("dark_gray", Color.DARK_GRAY);
    }

    public Process(String name, String colorName, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.color = parseColor(colorName);
    }

    public Process(String name, String colorName, int arrivalTime, int burstTime, int priority,int QT) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.QT = QT;
        this.fcaiFactor = 0.0;
        this.processed = false;
        this.color = parseColor(colorName);
    }

    public void addExecutionInterval(int startTime, int endTime) {
        executionIntervals.add(new int[]{startTime, endTime});
    }

    private Color parseColor(String colorName) {
        return predefinedColors.getOrDefault(colorName.toLowerCase(), Color.WHITE);
    }
    public void calculateFCAIFactor(double V1, double V2) {
        this.fcaiFactor = (10 - this.priority) +
                (this.arrivalTime / V1) +
                (this.remainingTime / V2);
    }

    public void updateQuantum(int unusedQuantum) {
        if (unusedQuantum > 0) {
            QT += unusedQuantum; // if preempted
        } else {
            QT += 2; //if quantum is fully used
        }
    }

    public void calculateTimes() {
        this.turnaroundTime = this.completionT - this.arrivalTime;
        this.waitingTime = this.turnaroundTime - this.burstTime;
    }
}