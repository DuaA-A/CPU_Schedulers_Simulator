package com.example;

import java.util.Comparator;
import java.util.List;

public class PriorityScheduling {
    
    public static void run(List<Process> processes) {
        processes.sort(Comparator.comparingInt((Process p) -> p.priority).thenComparingInt(p -> p.arrivalTime));
        int currT = 0;
        for (Process p : processes) {
            if (currT < p.arrivalTime) 
                currT = p.arrivalTime;
                
            int CT = currT + p.burstTime;
            p.turnaroundTime = CT - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
            currT = CT;
        }
        displayResults.print(processes);    
    }
}