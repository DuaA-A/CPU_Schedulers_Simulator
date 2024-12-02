package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter process name, arrival time, burst time, and priority: ");
            String name = sc.next();
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            int priority = sc.nextInt();
            processes.add(new Process(name, arrivalTime, burstTime, priority));
        }

        while (true) {
            System.out.println("\nSelect a Scheduling Algorithm:");
            System.out.println("1. Non-Preemptive Priority Scheduling");
            System.out.println("2. Non-Preemptive Shortest Job First (SJF)");
            System.out.println("3. Shortest Remaining Time First (SRTF)");
            System.out.println("4. FCAI Scheduling");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    PriorityScheduling.run(new ArrayList<>(processes));
                    break;
                case 2:
                    SJF.run(new ArrayList<>(processes));
                    break;
                case 3:
                    SRTF.run(new ArrayList<>(processes));
                    break;
                case 4:
                    // Youssef & AbdelRahman
                    break;
                case 5:
                    System.out.println("\nExiting. Goodbye!");
                    sc.close(); 
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
