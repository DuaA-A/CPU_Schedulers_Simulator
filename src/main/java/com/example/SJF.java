package com.example;

import java.util.ArrayList;
import java.util.List;

public class SJF {

    public static void run(List<Process> processes) {
        processes.sort((p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime));
        int currentTime = 0;  
        List<Process> completed = new ArrayList<>();  
        while (completed.size() < processes.size()) {
            int AGING_THRESHOLD = calculateAgingThreshold(processes, currentTime);; 
            Process next = null;
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && !completed.contains(p)) {
                    if (next == null || (p.burstTime < next.burstTime) || ((currentTime - p.arrivalTime) >= AGING_THRESHOLD && (currentTime - next.arrivalTime) < AGING_THRESHOLD)) {
                        next = p;
                    }
                }
            }
            if (next == null) {
                currentTime++;
                continue;
            }
            int startTime = currentTime;  
            int completionTime = currentTime + next.burstTime;  
            next.executionIntervals.add(new int[]{startTime, completionTime}); 
            
            next.turnaroundTime = completionTime - next.arrivalTime;
            next.waitingTime = next.turnaroundTime - next.burstTime;
            currentTime = completionTime;
            completed.add(next);
        }
        displayResults.print(completed);
        GanttChartSJF.display(completed); 
    }

    private static int calculateAgingThreshold(List<Process> processes, int currentTime) {
        int maxWaitTime = 0;
    
        for (Process p : processes) {
            if (p.arrivalTime <= currentTime) {
                int waitTime = currentTime - p.arrivalTime;
                maxWaitTime = Math.max(maxWaitTime, waitTime);
            }
        }
        if (maxWaitTime > 15) 
            return 5; 
        else if (maxWaitTime > 10) 
            return 10; 
        else 
            return 15;
    }
}
