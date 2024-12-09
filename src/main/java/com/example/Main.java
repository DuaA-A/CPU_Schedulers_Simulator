package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter the context switching time: ");
        int CS = sc.nextInt();
        System.out.print("Enter the Round Robin Time Quantum: ");
        int quantum = sc.nextInt();
        List<Process> processes = new ArrayList<>();

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
                takeInput(sc, n, processes);
                PriorityScheduling.run(new ArrayList<>(processes), CS);
                break;
            case 2:
                takeInput(sc, n, processes);
                SJF.run(new ArrayList<>(processes));
                break;
            case 3:
                takeInput(sc, n, processes);
                SRTF.run(new ArrayList<>(processes), CS);
                break;
            case 4:
                takeInputFCAI(sc, n, processes);
                FCAI.run(new ArrayList<>(processes));
                break;
            case 5:
                System.out.println("\nExiting. Goodbye!");
                sc.close();
                return;
            default:
                System.out.println("Invalid choice! Try again.");
        }

        sc.close();
    }

    public static void takeInput(Scanner sc, int n, List<Process> processes) {
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1) + ":");
            System.out.print("Process Name: ");
            String name = sc.next();
            System.out.print("Process Color (Choose from red, blue, green, yellow, cyan, magenta, orange, pink,\n gray, black, purple, brown, light_gray, dark_gray): ");
            String color = sc.next();
            System.out.print("Arrival Time: ");
            int arrivalTime = sc.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = sc.nextInt();
            System.out.print("Priority Number: ");
            int priority = sc.nextInt();
            processes.add(new Process(name, color, arrivalTime, burstTime, priority));
        }
    }public static void takeInputFCAI(Scanner sc, int n, List<Process> processes) {
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1) + ":");
            System.out.print("Process Name: ");
            String name = sc.next();
            System.out.print("Process Color (Choose from red, blue, green, yellow, cyan, magenta, orange, pink,\n gray, black, purple, brown, light_gray, dark_gray): ");
            String color = sc.next();
            System.out.print("Arrival Time: ");
            int arrivalTime = sc.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = sc.nextInt();
            System.out.print("Priority Number: ");
            int priority = sc.nextInt();
            System.out.print("quantum: ");
            int QT = sc.nextInt();
            processes.add(new Process(name, color, arrivalTime, burstTime, priority,QT));
        }
    }
}
