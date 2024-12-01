package com.example;

import java.util.List;

public class SRTF {
    
    public static void run(List<Process> processes) {
        int CT = 0, comp = 0;
        int[] remainingTime = processes.stream().mapToInt(p -> p.burstTime).toArray();
        boolean[] isCompleted = new boolean[processes.size()];

        while (comp < processes.size()) {
            int shortestI = -1, minRemainT = Integer.MAX_VALUE;

            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).arrivalTime <= CT && !isCompleted[i] && remainingTime[i] < minRemainT) {
                    shortestI = i;
                    minRemainT = remainingTime[i];
                }
            }
            if (shortestI == -1) {
                CT++;
                continue;
            }
            remainingTime[shortestI]--;
            CT++;
            if (remainingTime[shortestI] == 0) {
                comp++;
                isCompleted[shortestI] = true;
                Process p = processes.get(shortestI);
                p.turnaroundTime = CT - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
            }
        }

        displayResults.print(processes);
    }

    
}
