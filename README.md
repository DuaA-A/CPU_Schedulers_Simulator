<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
</head>
<body>
    <header>
        <h1>CPU Scheduler Simulator</h1>
        <p>A Java-based project implementing various CPU scheduling algorithms.</p>
    </header>
    <main>
        <h2>Overview</h2>
        <p>This repository contains implementations of various CPU scheduling algorithms, including:</p>
        <ul>
            <li>First-Come-First-Serve (FCFS)</li>
            <li>Shortest Job First (SJF)</li>
            <li>Shortest Remaining Time First (SRTF)</li>
            <li>Priority Scheduling</li>
        </ul>
    <h2>Files in the Repository</h2>
    <ul>
        <li><code>FCAI.java</code>: Handles First-Come-First-Serve scheduling.</li>
        <li><code>GanttChartFCAI.java</code>: Generates Gantt chart for FCFS scheduling.</li>
        <li><code>GanttChartPriority.java</code>: Generates Gantt chart for Priority Scheduling.</li>
        <li><code>GanttChartSJF.java</code>: Generates Gantt chart for SJF scheduling.</li>
        <li><code>GanttChartSRTF.java</code>: Generates Gantt chart for SRTF scheduling.</li>
        <li><code>Main.java</code>: Entry point of the project.</li>
        <li><code>PriorityScheduling.java</code>: Handles Priority Scheduling logic.</li>
        <li><code>Process.java</code>: Represents a process with attributes like arrival time, burst time, and priority.</li>
        <li><code>SJF.java</code>: Implements Shortest Job First scheduling.</li>
        <li><code>SRTF.java</code>: Implements Shortest Remaining Time First scheduling.</li>
        <li><code>displayResults.java</code>: Displays the results of scheduling algorithms.</li>
        <li><code>input.txt</code>: Input file containing process data for testing.</li>
        <li><code>inputFCAI.txt</code>: Alternative input file for FCFS scheduling.</li>
    </ul>
    <h2>How to Use</h2>
    <ol>
        <li>Compile the Java files using <code>javac</code>.</li>
        <li>Run the <code>Main.java</code> file to execute the program.</li>
        <li>Edit the <code>input.txt</code> file to test with custom process data.</li>
    </ol>
    <h2>Gantt Chart Generation</h2>
    <p>The project includes Gantt chart visualizations for each scheduling algorithm to better understand the process flow.</p>
    <h2>Dependencies</h2>
    <p>No external libraries are required. The project is built using core Java.</p>
    <h2>Contributors</h2>
    <p>Developed by:</p>
<ul>
    <li>Duaa </li>
    <li>Yousif</li>
    <li>Helana</li>
    <li>AbdelRahman</li>
</ul>
    <h2>License</h2>
    <p>This project is open-source. Feel free to use and modify it as needed.</p>

<footer>
    <p>&copy; 2025 CPU Scheduling Project</p>
</footer>

</body>
</html>
