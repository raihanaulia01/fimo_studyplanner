package com.example.fimostudyplanner.TasksFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fimostudyplanner.EditTaskActivity;
import com.example.fimostudyplanner.R;
import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.TaskData.TaskManager;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Context context;
    private List<Task> taskList;
    private TaskManager taskManager;
    private OnCheckedChangeListener onCheckedChangeListener;

    public TaskAdapter(Context context, OnCheckedChangeListener listener) {
        this.context = context;
        this.taskManager = new TaskManager(context);
        this.taskList = taskManager.getTasks();
        this.onCheckedChangeListener = listener;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.task_rv_item, parent, false);

        TaskViewHolder viewHolder = new TaskViewHolder(v);

        viewHolder.cbTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int position = viewHolder.getAdapterPosition();
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onItemCheckedChanged(
                        taskList.get(position).getId(),
                        isChecked);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskTitleTV.setText(task.getTitle());
        holder.taskDescTV.setText(task.getDescription());
        holder.taskDueTV.setText(task.getDueDate());
        holder.cbTask.setChecked(task.isCompleted());

        holder.cbTask.setOnClickListener(v -> {
            Log.d("RecyclerView", "Task Title: " + task.getTitle() + " | " + task.getId());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskTitleTV, taskDescTV, taskDueTV;
        ImageButton editBtn;
        CheckBox cbTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitleTV = itemView.findViewById(R.id.taskTitleTV);
            taskDescTV = itemView.findViewById(R.id.taskDescTV);
            taskDueTV = itemView.findViewById(R.id.taskDueTV);
            editBtn = itemView.findViewById(R.id.editBtn);
            cbTask = itemView.findViewById(R.id.cbTask);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public interface OnCheckedChangeListener {
        void onItemCheckedChanged(int taskId, boolean isChecked);
    }
}
