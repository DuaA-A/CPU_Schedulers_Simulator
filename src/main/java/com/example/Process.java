package com.example;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int priority;
    int waitingTime;
    int turnaroundTime;
    int remainingTime; 
    Color color; 
    List<int[]> executionIntervals = new ArrayList<>();
    
    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.color = generateRandomColor();  
    }

    public void addExecutionInterval(int startTime, int endTime) {
        executionIntervals.add(new int[] { startTime, endTime });
    }

    private Color generateRandomColor() {
        Random rand = new Random();
        int r, g, b;
        do {
            r = rand.nextInt(256);
            g = rand.nextInt(256);
            b = rand.nextInt(256);
            int brightness = (r + g + b) / 3;
            if (brightness<50 || brightness>205) 
                continue;

            break; 
        } while (true);
        return new Color(r, g, b);
    }
}
