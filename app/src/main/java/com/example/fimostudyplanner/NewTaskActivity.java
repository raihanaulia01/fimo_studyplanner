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

public class NewTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnAddTask;
    EditText etTaskTitle, etTaskDesc, etTaskDueDate;
    Spinner spinnerPriority;

    // sorted from low priority to high
    String[] priorities = {"Not Urgent & Unimportant", "Not Urgent & Important",
                            "Urgent & Unimportant", "Urgent & Important"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        etTaskTitle = findViewById(R.id.etTaskTitle);
        etTaskDesc = findViewById(R.id.etTaskDesc);
        etTaskDueDate = findViewById(R.id.etTaskDueDate);
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

        int taskPriority = findIndex(priorities, priority);
        Task task = new Task(taskTitle, taskDesc, taskDue, taskPriority, false);
        Log.d("info", "btnAddTaskOnClick: " + task.getTitle() + " Priority:" + task.getPriority());

        // go back to main activity and put extra to go to tasks fragment
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("GoTo", "TasksFragment");
        startActivity(intent);
    }

    private int findIndex(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
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