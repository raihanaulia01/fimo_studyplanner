package com.example.fimostudyplanner.TasksFragments;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fimostudyplanner.EditTaskActivity;
import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.R;

import java.util.List;

public class EisenhowerAdapter extends RecyclerView.Adapter<EisenhowerAdapter.EisenhowerViewHolder> {
    private Context context;
    private List<Task> taskList;
    private OnCheckedChangeListener onCheckedChangeListener;

    public EisenhowerAdapter(Context context, List<Task> taskList, OnCheckedChangeListener listener) {
        this.context = context;
        this.taskList = taskList;
        this.onCheckedChangeListener = listener;
    }


    @NonNull
    @Override
    public EisenhowerAdapter.EisenhowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.eisenhower_rv_item, parent, false);
        EisenhowerViewHolder viewHolder = new EisenhowerViewHolder(v);
        viewHolder.cbTaskEH.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int position = viewHolder.getAdapterPosition();
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onItemCheckedChanged(taskList.get(position).getId(), isChecked);
            }
        });

        viewHolder.constraintLayoutEH.setOnClickListener(v1 -> {
            int pos = viewHolder.getAdapterPosition();
            Intent i = new Intent(context, EditTaskActivity.class);
            i.putExtra("TaskId", taskList.get(pos).getId());
            Log.d("intent id", "onCreateViewHolder: " + taskList.get(pos).getId());
            context.startActivity(i);
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EisenhowerAdapter.EisenhowerViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.tvTitleEH.setText(task.getTitle());
        holder.tvDueEH.setText(task.getFormattedDueDate());
        holder.cbTaskEH.setChecked(task.isCompleted());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class EisenhowerViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitleEH, tvDueEH;
        CheckBox cbTaskEH;
        ConstraintLayout constraintLayoutEH;

        public EisenhowerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleEH = itemView.findViewById(R.id.tvTitleEH);
            tvDueEH = itemView.findViewById(R.id.tvDueEH);
            cbTaskEH = itemView.findViewById(R.id.cbTaskEH);
            constraintLayoutEH = itemView.findViewById(R.id.constraintLayoutEH);
        }
    }

    public interface OnCheckedChangeListener {
        void onItemCheckedChanged(int taskId, boolean isChecked);
    }
}
