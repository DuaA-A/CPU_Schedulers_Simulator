package com.example;

import java.util.List;

public class SRTF {
    public static void run(List<Process> processes, int CS) {
        int currT = 0, comp = 0;
        int[] remainingTime = processes.stream().mapToInt(p -> p.burstTime).toArray();
        boolean[] Completed = new boolean[processes.size()];
        int[] WT = new int[processes.size()]; 
        Process lastP = null;
        int cS = CS; 
        while (comp < processes.size()) {
            int agingT = calcAgingT(processes, currT);
            int shortestI = -1, minRemainT = Integer.MAX_VALUE;
            for (int i = 0; i < processes.size(); i++) {
                if (!Completed[i] && processes.get(i).arrivalTime <= currT) {
                    WT[i]++;
                }
            }
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).arrivalTime <= currT && !Completed[i]) {
                    if ((remainingTime[i] < minRemainT) || 
                        ((currT - processes.get(i).arrivalTime) >= agingT && (shortestI != -1 && (currT - processes.get(shortestI).arrivalTime) < agingT))) {
                        shortestI = i;
                        minRemainT = remainingTime[i];
                    }
                }
            }
            if (shortestI == -1) { 
                currT++;
                continue;
            }
            Process currP = processes.get(shortestI);
            if (lastP != null && lastP != currP) 
                currT += cS; 
            
            currP.addExecutionInterval(currT, currT + 1);
            remainingTime[shortestI]--;
            currT++;
            if (remainingTime[shortestI] == 0) { 
                comp++;
                Completed[shortestI] = true;
                currP.turnaroundTime = currT - currP.arrivalTime;
                currP.waitingTime = currP.turnaroundTime - currP.burstTime;
            }
            lastP = currP; 
        }
        displayResults.print(processes);
        GanttChartSRTF.display(processes);
    }

    private static int calcAgingT(List<Process> processes, int currentTime) {
        int maxWT = 0;
        for (Process p : processes) {
            if (p.arrivalTime <= currentTime) {
                int WT = currentTime - p.arrivalTime;
                maxWT = Math.max(maxWT, WT);
            }
        }
        if (maxWT > 15) 
            return 5; 
        else if (maxWT > 10) 
            return 10; 
        else 
            return 15;
    }
}