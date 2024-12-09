package com.example;

import java.io.File;
import java.io.FileNotFoundException;
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
        // System.out.print("Enter the Round Robin Time Quantum: ");
        // int quantum = sc.nextInt();
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
                takeInput(n,processes);
                PriorityScheduling.run(new ArrayList<>(processes), CS);
                break;
            case 2:
                takeInput(n, processes);
                SJF.run(new ArrayList<>(processes));
                break;
            case 3:
                takeInput(n, processes);
                SRTF.run(new ArrayList<>(processes), CS);
                break;
            case 4:
                takeInputFCAI(n, processes);
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

    public static void takeInput(int n, List<Process> processes) {
        String filePath = "src/main/java/com/example/input.txt";
                try (Scanner fileScanner = new Scanner(new File(filePath))) {
                while (fileScanner.hasNextLine() && processes.size() < n) {
                    String[] data = fileScanner.nextLine().split(",");
                    String name = data[0];
                    String color = data[1];
                    int arrivalTime = Integer.parseInt(data[2]);
                    int burstTime = Integer.parseInt(data[3]);
                    int priority = Integer.parseInt(data[4]);
                    processes.add(new Process(name, color, arrivalTime, burstTime, priority));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please check the file path.");
            }

    }

    public static void takeInputFCAI(int n, List<Process> processes) {
        String filePath = "/src/main/java/com/example/input.txt";
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine() && processes.size() < n) {
                String[] data = fileScanner.nextLine().split(",");
                String name = data[0];
                String color = data[1];
                int arrivalTime = Integer.parseInt(data[2]);
                int burstTime = Integer.parseInt(data[3]);
                int priority = Integer.parseInt(data[4]);
                int QT = Integer.parseInt(data[5]);
                processes.add(new Process(name, color, arrivalTime, burstTime, priority,QT));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the file path.");
        }

    }
}
