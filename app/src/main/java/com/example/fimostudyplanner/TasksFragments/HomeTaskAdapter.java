package com.example.fimostudyplanner.TasksFragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fimostudyplanner.EditTaskActivity;
import com.example.fimostudyplanner.HomeFragment;
import com.example.fimostudyplanner.TaskData.DateConverter;
import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.TaskData.TaskManager;
import com.example.fimostudyplanner.R;

import java.util.List;

public class HomeTaskAdapter extends RecyclerView.Adapter<HomeTaskAdapter.HomeTaskViewHolder> {
    private Context context;
    private List<Task> taskList;
    private TaskManager taskManager;
    private HomeTaskAdapter.OnCheckedChangeListener onCheckedChangeListener;

    public HomeTaskAdapter(Context context, OnCheckedChangeListener listener) {
        this.context = context;
        this.taskManager = new TaskManager(context);
        this.taskList = taskManager.getTasks();
        this.onCheckedChangeListener = listener;
    }

    @NonNull
    @Override
    public HomeTaskAdapter.HomeTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.task_rv_home_item,
                parent, false);

        HomeTaskViewHolder viewHolder = new HomeTaskViewHolder(v);

        viewHolder.cbTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int position = viewHolder.getAdapterPosition();
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onItemCheckedChange(
                        taskList.get(position).getId(), isChecked);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTaskAdapter.HomeTaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskTitleTV.setText(task.getTitle());
        holder.taskDueTV.setText(DateConverter.convertFromEpoch(task.getDueDate(), DateConverter.DEFAULT_FORMAT));
        holder.cbTask.setChecked(task.isCompleted());

        holder.editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditTaskActivity.class);
            intent.putExtra("TaskId", task.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class HomeTaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskTitleTV, taskDueTV;
        ImageButton editBtn;
        CheckBox cbTask;
        public HomeTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitleTV = itemView.findViewById(R.id.taskTitleTV);
            taskDueTV = itemView.findViewById(R.id.taskDueTV);
            editBtn = itemView.findViewById(R.id.editBtn);
            cbTask = itemView.findViewById(R.id.cbTask);
        }
    }

    public interface OnCheckedChangeListener {
        void onItemCheckedChange(int taskId, boolean isChecked);
    }
}
