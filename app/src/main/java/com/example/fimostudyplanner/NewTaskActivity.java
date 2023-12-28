package com.example.fimostudyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.fimostudyplanner.TaskData.DateConverter;
import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.TaskData.TaskManager;

public class NewTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnAddTask;
    EditText etTaskTitle, etTaskDesc, etTaskDueDate;
    Spinner spinnerPriority;

    // sorted from low priority to high
    public static final String[] priorities = {"Not Urgent & Unimportant", "Urgent & Unimportant",
            "Not Urgent & Important", "Urgent & Important"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        etTaskTitle = findViewById(R.id.etAddTitle);
        etTaskDesc = findViewById(R.id.etAddDesc);
        etTaskDueDate = findViewById(R.id.etAddDueDate);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        btnAddTask = findViewById(R.id.btnAddTask);

        spinnerPriority.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, priorities);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPriority.setAdapter(ad);
    }

    public void btnAddTaskOnClick(View v) {
        String taskTitle = etTaskTitle.getText().toString();
        String taskDesc = etTaskDesc.getText().toString();
        String taskDue = etTaskDueDate.getText().toString();
        String priority = spinnerPriority.getItemAtPosition(spinnerPriority.getSelectedItemPosition()).toString();

        int taskPriority = findIndex(priority);
        Task task = new Task(taskTitle, taskDesc, DateConverter.convertToEpoch(taskDue), taskPriority, false);
        Log.d("info", "task title: " + task.getTitle() + " priority: " + task.getPriority());

        TaskManager taskManager = new TaskManager(this);
        taskManager.addTask(task);

        // go back to main activity and put extra to go to tasks fragment
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GoTo", "TasksFragment");
        startActivity(intent);
    }

    private int findIndex(String target) {
        for (int i = 0; i < NewTaskActivity.priorities.length; i++) {
            if (NewTaskActivity.priorities[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}