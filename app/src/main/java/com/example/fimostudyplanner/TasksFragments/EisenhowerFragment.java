package com.example.fimostudyplanner.TasksFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fimostudyplanner.NewTaskActivity;
import com.example.fimostudyplanner.R;
import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.TaskData.TaskManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EisenhowerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EisenhowerFragment extends Fragment implements EisenhowerAdapter.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FloatingActionButton fabAddTaskEH;
    private RecyclerView rvPriority3;
    private RecyclerView rvPriority2;
    private RecyclerView rvPriority1;
    private RecyclerView rvPriority0;

    private EisenhowerAdapter ehAdapterPriority3;
    private EisenhowerAdapter ehAdapterPriority2;
    private EisenhowerAdapter ehAdapterPriority1;
    private EisenhowerAdapter ehAdapterPriority0;

    private TaskManager taskManager;

    public EisenhowerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EisenhowerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EisenhowerFragment newInstance(String param1, String param2) {
        EisenhowerFragment fragment = new EisenhowerFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_eisenhower, container, false);
        fabAddTaskEH = rootView.findViewById(R.id.fabAddTaskEH);
        rvPriority3 = rootView.findViewById(R.id.rvPriority3);
        rvPriority2 = rootView.findViewById(R.id.rvPriority2);
        rvPriority1 = rootView.findViewById(R.id.rvPriority1);
        rvPriority0 = rootView.findViewById(R.id.rvPriority0);

        List<Task> taskList = taskManager.getTasks();

        List<Task> tlPriority3 = taskList.stream()
                .filter(task -> task.getPriority() == 3).collect(Collectors.toList());
        List<Task> tlPriority2 = taskList.stream()
                .filter(task -> task.getPriority() == 2).collect(Collectors.toList());
        List<Task> tlPriority1 = taskList.stream()
                .filter(task -> task.getPriority() == 1).collect(Collectors.toList());
        List<Task> tlPriority0 = taskList.stream()
                .filter(task -> task.getPriority() == 0).collect(Collectors.toList());

        ehAdapterPriority3 = new EisenhowerAdapter(getContext(), tlPriority3, this);
        ehAdapterPriority2 = new EisenhowerAdapter(getContext(), tlPriority2, this);
        ehAdapterPriority1 = new EisenhowerAdapter(getContext(), tlPriority1, this);
        ehAdapterPriority0 = new EisenhowerAdapter(getContext(), tlPriority0, this);

        rvPriority3.setAdapter(ehAdapterPriority3);
        rvPriority2.setAdapter(ehAdapterPriority2);
        rvPriority1.setAdapter(ehAdapterPriority1);
        rvPriority0.setAdapter(ehAdapterPriority0);

        rvPriority3.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPriority2.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPriority1.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPriority0.setLayoutManager(new LinearLayoutManager(getContext()));

        fabAddTaskEH.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), NewTaskActivity.class);
            startActivity(i);
        });

        return rootView;
    }

    @Override
    public void onItemCheckedChanged(int taskId, boolean isChecked) {
        taskManager.setTaskIsCompleted(taskId, isChecked);
        Log.d("task checked", "onItemCheckedChanged: " + taskId + " | " + isChecked);
    }
}