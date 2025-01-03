package com.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
public class PriorityScheduling {
    public static void run(List<Process> processes, int CS) {
        List<Process> completedProcesses = new ArrayList<>();
        List<Process> readyQueue = new ArrayList<>();
        int currT = 0;
        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            for (Iterator<Process> it = processes.iterator(); it.hasNext(); ) {
                Process p = it.next();
                if (p.arrivalTime <= currT) {
                    readyQueue.add(p);
                    it.remove();
                }
            }
            readyQueue.sort(Comparator.comparingInt((Process p) -> p.priority));
            if (!readyQueue.isEmpty()) {
                Process p = readyQueue.remove(0);
                if (!completedProcesses.isEmpty()) 
                    currT += CS;
                int startTime = currT;
                int endTime = startTime + p.burstTime;
                p.executionIntervals.add(new int[]{startTime, endTime});
                p.turnaroundTime = currT + p.burstTime + CS - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
                currT = endTime;
                completedProcesses.add(p);
            } else {
                currT++;
            }
        }
        displayResults.print(completedProcesses);
        GanttChartPriority.display(completedProcesses);
    }
}
