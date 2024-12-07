package com.example;
import java.util.List;

public class SRTF {
    public static void run(List<Process> processes) {
        int currT = 0, comp = 0;
        int[] remainingTime = processes.stream().mapToInt(p -> p.burstTime).toArray();
        boolean[] isCompleted = new boolean[processes.size()];
        int[] waitTime = new int[processes.size()]; 
        Process lastProcess = null;
        int contextSwitchOverhead = 1; 

        while (comp < processes.size()) {
            int agingThreshold = calculateAgingThreshold(processes, currT);
            int shortestI = -1, minRemainT = Integer.MAX_VALUE;
            for (int i = 0; i < processes.size(); i++) {
                if (!isCompleted[i] && processes.get(i).arrivalTime <= currT) {
                    waitTime[i]++;
                }
            }
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).arrivalTime <= currT && !isCompleted[i]) {
                    if ((remainingTime[i] < minRemainT) || 
                        ((currT - processes.get(i).arrivalTime) >= agingThreshold && (currT - processes.get(shortestI).arrivalTime) < agingThreshold)) {
                        shortestI = i;
                        minRemainT = remainingTime[i];
                    }
                }
            }
            if (shortestI == -1) { 
                currT++;
                continue;
            }
            Process currentProcess = processes.get(shortestI);
            if (lastProcess != null && lastProcess != currentProcess) 
                currT += contextSwitchOverhead; 
            
            currentProcess.addExecutionInterval(currT, currT + 1);
            remainingTime[shortestI]--;
            currT++;
            if (remainingTime[shortestI] == 0) { 
                comp++;
                isCompleted[shortestI] = true;
                currentProcess.turnaroundTime = currT - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
            }
            lastProcess = currentProcess; 
        }
        displayResults.print(processes);
        GanttChartSRTF.display(processes);
    }

    private static int calculateAgingThreshold(List<Process> processes, int currentTime) {
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
