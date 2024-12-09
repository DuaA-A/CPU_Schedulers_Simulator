package com.example;

import java.util.*;

public class FCAI {

    public static void run(List<Process> processes) {
        int currT = 0;
        int done = 0;

        int lastArrivalTime = processes.get(processes.size() - 1).arrivalTime;
        int maxBurstTime = processes.stream().mapToInt(p -> p.burstTime).max().orElse(0);
        double V1 = lastArrivalTime / 10.0;
        double V2 = maxBurstTime / 10.0;

        for (Process p : processes) {
            p.calculateFCAIFactor(V1,V2);
        }

        processes.sort(Comparator.comparingInt(process -> process.arrivalTime));

        Queue<Process> queue = new LinkedList<>();
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(process -> (int) process.fcaiFactor));

        List<String> executionOrder = new ArrayList<>();
        List<int[]> quantumHistory2D = new ArrayList<>();

        for (Process p : processes) {
            quantumHistory2D.add(new int[]{p.QT});
        }

        for (Process p : processes) {
            if (p.arrivalTime <= currT) {
                pq.add(p);
                queue.add(p);
                p.processed = true;
            }
        }

        Process currentProcess = pq.poll();
        queue.remove(currentProcess);

        while (done < processes.size()) {

            if (done == processes.size() - 1) {
                currT += currentProcess.remainingTime;
                currentProcess.addExecutionInterval((currT - currentProcess.remainingTime), currT);
                executionOrder.add(currentProcess.name);

                currentProcess.turnaroundTime = currT - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                for (Process p : processes) {
                    if (p.arrivalTime <= currT && p.remainingTime > 0 && !p.processed && !pq.contains(p)) {
                        p.calculateFCAIFactor(V1, V2);
                        p.processed = true;
                        pq.add(p);
                        queue.add(p);
                    }
                }
                break;
            }

            int executionTime = Math.min(currentProcess.remainingTime, (int) Math.ceil(0.4 * currentProcess.QT));

            currT += executionTime;
            currentProcess.remainingTime -= executionTime;

            for (Process p : processes) {
                if (p.arrivalTime <= currT && p.remainingTime > 0 && !p.processed && !pq.contains(p)) {
                    p.calculateFCAIFactor(V1, V2);
                    p.processed = true;
                    pq.add(p);
                    queue.add(p);
                }
            }

            int unusedQT = currentProcess.QT - executionTime;

            Process lowestFCAI = pq.peek();

            int currBeforeInc = currT;
            while (lowestFCAI == null || (lowestFCAI.fcaiFactor >= currentProcess.fcaiFactor && unusedQT > 0 && currentProcess.remainingTime > 0)) {
                currT++;
                currentProcess.remainingTime--;
                unusedQT--;

                for (Process p : processes) {
                    if (p.arrivalTime <= currT && p.remainingTime > 0 && !p.processed && !pq.contains(p)) {
                        p.calculateFCAIFactor(V1, V2);
                        p.processed = true;
                        pq.add(p);
                        queue.add(p);
                    }
                }

                lowestFCAI = pq.peek();
            }

            if (currT == currBeforeInc) {
                currentProcess.addExecutionInterval((currT - executionTime), currT);
            }
            currentProcess.addExecutionInterval((currBeforeInc - executionTime), currT);
            executionOrder.add(currentProcess.name);

            if (currentProcess.remainingTime == 0) {
                done++;
                currentProcess.turnaroundTime = currT - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                for (Process p : processes) {
                    if (p.arrivalTime <= currT && p.remainingTime > 0 && !p.processed && !pq.contains(p)) {
                        p.calculateFCAIFactor(V1, V2);
                        p.processed = true;
                        pq.add(p);
                        queue.add(p);
                    }
                }
                if (!queue.isEmpty()) {
                    currentProcess = queue.poll();
                    pq.remove(currentProcess);
                }
            } else {
                currentProcess.calculateFCAIFactor(V1, V2);
                currentProcess.updateQuantum(unusedQT);

                for (int i = 0; i < processes.size(); i++) {
                    Process p = processes.get(i);
                    int[] quantumUpdate = new int[quantumHistory2D.get(i).length + 1];
                    System.arraycopy(quantumHistory2D.get(i), 0, quantumUpdate, 0, quantumHistory2D.get(i).length);
                    quantumUpdate[quantumUpdate.length - 1] = p.QT;
                    quantumHistory2D.set(i, quantumUpdate);
                }

                queue.add(currentProcess);
                pq.add(currentProcess);

                if (unusedQT == 0) {
                    currentProcess = queue.poll();
                    pq.remove(currentProcess);
                } else {
                    Process temp = pq.poll();
                    queue.remove(temp);
                    currentProcess = temp;
                }
            }
        }

        displayResults(processes, quantumHistory2D, executionOrder);
        GanttChartFCAI.display(processes);
    }

    public static void displayResults(List<Process> processes, List<int[]> quantumHistory2D, List<String> executionOrder) {
        System.out.println("Execution Order");
        for (int i = 0; i < executionOrder.size(); i++) {
            if (i == executionOrder.size() - 1) {
                System.out.print(executionOrder.get(i));
            } else {
                System.out.print(executionOrder.get(i) + "->");
            }
        }

        System.out.println("\n\nWaiting Time for Each Process:");
        processes.forEach(p -> System.out.println("P" + p.name + ": " + p.waitingTime));

        System.out.println("\nTurnaround Time for Each Process:");
        processes.forEach(p -> System.out.println(p.name + ": " + p.turnaroundTime));

        System.out.println("\nQuantum History Updates:");
        for (int i = 0; i < quantumHistory2D.size(); i++) {
            int[] quantumHistory = quantumHistory2D.get(i);
            System.out.print(processes.get(i).name + ": ");

            for (int j = 0; j < quantumHistory.length; j++) {
                if (j == quantumHistory.length - 1) {
                    System.out.print(quantumHistory[j]);
                } else {
                    System.out.print(quantumHistory[j] + "->");
                }
            }

            System.out.println();
        }

        double avgWaitingTime = processes.stream().mapToInt(p -> p.waitingTime).average().orElse(0);
        double avgTurnaroundTime = processes.stream().mapToInt(p -> p.turnaroundTime).average().orElse(0);
        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
