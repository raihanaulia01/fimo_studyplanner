package com.example.fimostudyplanner;

import android.os.Bundle;

import androidx.annotation.LongDef;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.TaskData.TaskManager;
import com.example.fimostudyplanner.TasksFragments.HomeTaskAdapter;
import com.example.fimostudyplanner.TasksFragments.TaskAdapter;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeTaskAdapter.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private HomeTaskAdapter taskAdapter;
    private TextView tvTaskCount;
    private TextView tvGreeting;
    private TextView tvTaskProgress;
    private LinearProgressIndicator taskProgressIndicator;
    private TaskManager taskManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        taskManager = new TaskManager(getContext());

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.rvTaskHome);
        tvTaskCount = rootView.findViewById(R.id.tvTaskCount);

        tvTaskProgress = rootView.findViewById(R.id.tvTaskProgress);
        taskProgressIndicator = rootView.findViewById(R.id.taskProgressIndicator);

        taskProgressIndicator.setProgress(getTaskDoneProgress());
        tvTaskProgress.setText(taskManager.getNumberOfTasksDone() + " out of "
                + taskManager.getTasks().size() + " tasks done!");

        taskAdapter = new HomeTaskAdapter(getContext(), this);
        recyclerView.setAdapter(taskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TaskManager taskManager = new TaskManager(getContext());
        List<Task> taskList = taskManager.getTasks();
        tvTaskCount.setText(taskList.size() + " tasks today.");

        return rootView;
    }

    private int getTaskDoneProgress() {
        TaskManager taskManager = new TaskManager(getContext());
        if (taskManager.getTasks().size() == 0) {
            return 0;
        }
        double progressPercentage = ((double) (taskManager.getNumberOfTasksDone()) / taskManager.getTasks().size()) * 100;
        return (int) Math.round(progressPercentage);
    }

    @Override
    public void onItemCheckedChange(int taskId, boolean isChecked) {
        taskManager.setTaskIsCompleted(taskId, isChecked);
        Log.d("task checked", "HomeFragment onItemCheckedChanged: " + taskId + " | " + isChecked);

        taskProgressIndicator.setProgress(getTaskDoneProgress(), true);
        tvTaskProgress.setText(taskManager.getNumberOfTasksDone() + " out of "
                + taskManager.getTasks().size() + " tasks done!");
    }
}