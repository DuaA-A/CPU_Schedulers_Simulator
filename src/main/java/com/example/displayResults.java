package com.example;

import java.util.List;

public class displayResults {
    public static void print(List<Process> processes) {
        System.out.println("\nProcess Exec order & Details:");
        int tWT = 0, tTurnaroundT = 0;
        for (Process p : processes) {
            tWT += p.waitingTime;
            tTurnaroundT += p.turnaroundTime;
            System.out.println(p.name + " -> Waiting Time: " + p.waitingTime + ", Turnaround Time: " + p.turnaroundTime);
        }
        System.out.println("\nAvg waiting time: " + (double) tWT / processes.size());
        System.out.println("Avg turnaround time: " + (double) tTurnaroundT / processes.size());
    }

    public static void calculateAverages(List<Process> processes) {
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
        }

        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}

