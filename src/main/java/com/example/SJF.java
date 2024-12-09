    package com.example;

    import java.util.*;

    public class SJF {
        public static void run(List<Process> processes) {
            PriorityQueue<Process> queue, pq;
            ArrayList<Process> completed = new ArrayList<>();

            pq = new PriorityQueue<>(
                    (p1, p2) -> (p1.arrivalTime == p2.arrivalTime) ? p1.burstTime - p2.burstTime : p1.arrivalTime - p2.arrivalTime
            );

            for (int i = 0; i < processes.size(); i++) {
                pq.add(processes.get(i));
            }

            queue = new PriorityQueue<>(
                    (p1, p2) -> (p1.priority == p2.priority) ? p1.burstTime - p2.burstTime : p2.priority - p1.priority
            );

            // Aging Mechanism
            Process[] priorityToZero = pq.toArray(new Process[0]);
            pq.clear();
            for (Process p : priorityToZero) {
                p.priority = 0;
                pq.add(p);
            }

            int AGING_THRESHOLD = 5;
            int priorityTime = AGING_THRESHOLD;

            while (!pq.isEmpty()) {
                Process process = pq.poll();
                if (completed.isEmpty())
                    process.completionT = process.arrivalTime + process.burstTime;
                else
                    process.completionT = completed.get(completed.size() - 1).completionT + process.burstTime;
                completed.add(process);

                int time = process.arrivalTime + process.burstTime;
                while (!pq.isEmpty() && pq.peek().arrivalTime <= time) {
                    queue.add(pq.poll());
                }

                while (!queue.isEmpty()) {
                    while (!pq.isEmpty() && pq.peek().arrivalTime <= process.completionT) {
                        queue.add(pq.poll());
                    }
                    if (process.completionT >= priorityTime) {
                        Process[] pr = queue.toArray(new Process[0]);
                        queue.clear();
                        for (Process p : pr) {
                            if (p.arrivalTime + process.completionT <= AGING_THRESHOLD)
                                p.priority++;
                        }
                        for (Process p : pr)
                            queue.add(p);
                        priorityTime += AGING_THRESHOLD;
                    } else {
                        Process process2 = queue.poll();
                        process2.completionT = process.completionT + process2.burstTime;
                        process = process2;
                        completed.add(process);
                    }
                    if(process == processes.get(3)){
                        process.calculateTimes();
                        process.addExecutionInterval(process.arrivalTime + process.waitingTime, process.completionT);
                    }
                    else{
                        process.calculateTimes();
                        process.addExecutionInterval(process.waitingTime, process.completionT);
                    }


                }

                for (Process p : completed) {
                    p.calculateTimes();
                }
                displayResults.print(completed);
                GanttChartSJF.display(processes);
            }

            displayResults.calculateAverages(completed);
        }
    }