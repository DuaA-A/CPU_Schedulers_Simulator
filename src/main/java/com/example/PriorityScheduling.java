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
        
            int startTime = currT;
            int endTime = startTime + p.burstTime;
            
            p.executionIntervals.add(new int[]{startTime, endTime}); 
            p.turnaroundTime = endTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
            currT = endTime;
        }
        
        displayResults.print(processes);  
        GanttChartPriority.display(processes); 

    }
}