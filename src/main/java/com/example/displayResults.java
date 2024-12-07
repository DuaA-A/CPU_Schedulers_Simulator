package com.example;

import java.util.List;

public class displayResults {
    public static void print(List<Process> processes) {
        System.out.println("\nProcess Execution Order & Details:");
        int tWT = 0, tTurnaroundT = 0;

        for (Process p : processes) {
            tWT += p.waitingTime;
            tTurnaroundT += p.turnaroundTime;
            System.out.println(p.name + " -> Waiting Time: " + p.waitingTime + ", Turnaround Time: " + p.turnaroundTime);
        }

        double avgWT = (double) tWT / processes.size();
        double avgTurnaroundT = (double) tTurnaroundT / processes.size();

        System.out.println("\nAvg waiting time: " + Math.ceil(avgWT));
        System.out.println("Avg turnaround time: " + Math.ceil(avgTurnaroundT));
    }
}
