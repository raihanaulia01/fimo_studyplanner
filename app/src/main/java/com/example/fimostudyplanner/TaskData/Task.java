package com.example.fimostudyplanner.TaskData;

public class Task {
    private int id;
    private String title;
    private String description;
    private long dueDate;

    // 0 = not urgent & unimportant, 1 = urgent & unimportant, 2 = not urgent & important, 3 = urgent & important
    private int priority;
    private boolean isCompleted;

    public Task(String title, String description, long dueDate, int priority, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getFormattedDueDate() {
        return DateConverter.convertFromEpoch(dueDate, DateConverter.DEFAULT_FORMAT);
    }
}
