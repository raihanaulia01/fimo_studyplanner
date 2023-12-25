package com.example.fimostudyplanner.TaskData;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String TASKS_KEY = "tasks";
    private Gson gson;
    private SharedPreferences sharedPreferences;

    public TaskManager(Context context) {
        gson = new Gson();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveTasks(List<Task> tasks) {
        String tasksJson = gson.toJson(tasks);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TASKS_KEY, tasksJson);
        editor.apply();
    }

    public void addTask(Task task) {
        List<Task> taskList = getTasks();
        int id;
        if (taskList.size() != 0) {
            id = taskList.get(taskList.size()-1).getId() + 1;
        } else {
            id = 0;
        }
        task.setId(id);
        taskList.add(task);
        saveTasks(taskList);
    }

    public List<Task> getTasks() {
        String tasksJson = sharedPreferences.getString(TASKS_KEY, null);

        if (tasksJson != null) {
            Type tasksListType = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(tasksJson, tasksListType);
        } else {
            return new ArrayList<>();
        }
    }

    public void updateTask(Task updatedTask) {
        List<Task> tasks = getTasks();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getId() == updatedTask.getId()) {
                // Update the properties of the task
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setDueDate(updatedTask.getDueDate());
                task.setPriority(updatedTask.getPriority());
                task.setCompleted(updatedTask.isCompleted());

                // Save the updated list
                saveTasks(tasks);
                return; // Exit the loop once the task is updated
            }
        }
    }
}
