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
import android.widget.Toast;

import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.TaskData.TaskManager;

public class EditTaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TaskManager taskManager;
    EditText editTitleET, editDescET, editDueET;
    Spinner editSpPriority;
    Button editBtn;
    int editTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent intent = getIntent();
        editTaskId = intent.getIntExtra("TaskId", -1);

        taskManager = new TaskManager(this);

        editTitleET = findViewById(R.id.etEditTitle);
        editDescET = findViewById(R.id.etEditDesc);
        editDueET = findViewById(R.id.etEditDueDate);
        editSpPriority = findViewById(R.id.editSpPriority);
        editBtn = findViewById(R.id.btnEditTask);

        editSpPriority.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, NewTaskActivity.priorities);

        editSpPriority.setAdapter(ad);
    }

    public void btnEditTaskOnClick(View v) {
        String taskTitle = editTitleET.getText().toString();
        String taskDesc = editDescET.getText().toString();
        String taskDue = editDueET.getText().toString();
        String priority = editSpPriority.getItemAtPosition(editSpPriority
                .getSelectedItemPosition()).toString();

        int taskPriority = findIndex(priority);
        Task newTask = new Task(taskTitle, taskDesc, taskDue, taskPriority, false);
        if (editTaskId != -1){
            newTask.setId(editTaskId);
            taskManager.updateTask(newTask);
        } else {
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText("Error: invalid ID");
            toast.show();
        }
//        go back to TasksFragment in MainActivity
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