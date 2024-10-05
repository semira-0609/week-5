package com.example.week5;

public class RecentActivity {

    private String taskName;
    private String timeCompleted;

    public RecentActivity(String taskName, String timeCompleted) {
        this.taskName = taskName;
        this.timeCompleted = timeCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTimeCompleted() {
        return timeCompleted;
    }
}
