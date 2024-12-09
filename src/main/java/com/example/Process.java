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

    public void addExecutionInterval(int startTime, int endTime) {
        executionIntervals.add(new int[]{startTime, endTime});
    }

    private Color parseColor(String colorName) {
        return predefinedColors.getOrDefault(colorName.toLowerCase(), Color.WHITE);
    }
}