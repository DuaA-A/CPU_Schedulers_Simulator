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
        int agingT = 5;

        while (comp < processes.size()) {
            int shortestI = -1, minRemainT = Integer.MAX_VALUE;
            for (int i = 0; i < processes.size(); i++) {
                if (!Completed[i] && processes.get(i).arrivalTime <= currT) {
                    WT[i]++;
                }
            }
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).arrivalTime <= currT && !Completed[i]) {
                    if (WT[i] >= agingT) {
                        if (shortestI == -1 || WT[i] > WT[shortestI] || 
                            (WT[i] == WT[shortestI] && remainingTime[i] < remainingTime[shortestI])) {
                            shortestI = i;
                        }
                    } else if (remainingTime[i] < minRemainT) {
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
            if (lastP != null && lastP != currP) {
                
                shortestI = -1;
                minRemainT = Integer.MAX_VALUE;
                for (int i = 0; i < processes.size(); i++) {
                    if (processes.get(i).arrivalTime <= currT && !Completed[i] && remainingTime[i] < minRemainT) {
                        shortestI = i;
                        minRemainT = remainingTime[i];
                    }
                }
                if (shortestI != -1 && processes.get(shortestI) != lastP) {
                    currP = processes.get(shortestI);
                    currT += cS;
                }else
                    currP = processes.get(shortestI);
                
            }
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
}
