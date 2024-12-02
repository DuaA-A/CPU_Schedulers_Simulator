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
    Color color;  // Store color here
    
    List<int[]> executionIntervals = new ArrayList<>();
    
    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.color = generateRandomColor();  // Set color once during initialization
    }

    public void addExecutionInterval(int startTime, int endTime) {
        executionIntervals.add(new int[] { startTime, endTime });
    }

    // Color generation method ensuring not black or white
    private Color generateRandomColor() {
        Random rand = new Random();
        int r, g, b;

        do {
            // Generate random RGB values between 0 and 255
            r = rand.nextInt(256);
            g = rand.nextInt(256);
            b = rand.nextInt(256);

            // Ensure the color is not too close to black or white
            int brightness = (r + g + b) / 3;  // Average RGB for brightness

            // If the color is too dark or too light, regenerate it
            if (brightness < 50 || brightness > 205) {
                continue;  // Too dark or too light, try again
            }

            break; // Valid color found, exit the loop
        } while (true);

        return new Color(r, g, b);
    }
}
