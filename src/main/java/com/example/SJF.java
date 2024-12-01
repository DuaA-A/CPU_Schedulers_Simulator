package com.example;

import java.util.ArrayList;
import java.util.List;

public class SJF {

    public static void run(List<Process> processes) {
       
        processes.sort((p1, p2) -> Integer.compare(p1.arrivalTime, p2.arrivalTime));

        int currentTime = 0;  
        List<Process> completed = new ArrayList<>();  
        final int AGING_THRESHOLD = 5; 

        while (completed.size() < processes.size()) {
            Process next = null;

            for (Process p : processes) {
            
                if (p.arrivalTime <= currentTime && !completed.contains(p)) {
                    if (next == null || 
                        (p.burstTime < next.burstTime) || 
                        ((currentTime - p.arrivalTime) >= AGING_THRESHOLD && (currentTime - next.arrivalTime) < AGING_THRESHOLD)) {
                        next = p;
                    }
                }
            }

            if (next == null) {
                currentTime++;
                continue;
            }

            int completionTime = currentTime + next.burstTime;
            next.turnaroundTime = completionTime - next.arrivalTime;
            next.waitingTime = next.turnaroundTime - next.burstTime;
            currentTime = completionTime;
            completed.add(next);
        }

       
        displayResults.print(completed);
    }

  
}