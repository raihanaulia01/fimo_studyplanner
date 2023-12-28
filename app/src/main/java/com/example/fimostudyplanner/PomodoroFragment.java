package com.example.fimostudyplanner;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PomodoroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PomodoroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tvRemainingTime;
    ProgressBar progressBar;
    Button btnTimerStart;
    Button btnTimerReset;
    private static final long DEFAULT_TIMER_TIME = 1800000;
    long timeInMillis = 1800000;
    CountDownTimer countDownTimer;
    ObjectAnimator animation;

    public PomodoroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PomodoroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PomodoroFragment newInstance(String param1, String param2) {
        PomodoroFragment fragment = new PomodoroFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pomodoro, container, false);
        progressBar = rootView.findViewById(R.id.circularProgressBar);
        btnTimerStart = rootView.findViewById(R.id.btnPomodoroStart);
        btnTimerReset = rootView.findViewById(R.id.btnPomodoroReset);
        tvRemainingTime = rootView.findViewById(R.id.tvRemainingTime);
        tvRemainingTime.setText(convertMillisToMinutesSeconds(timeInMillis));

        btnTimerStart.setOnClickListener(v -> startTimer());

        btnTimerReset.setOnClickListener(v -> resetTimer());

        return rootView;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvRemainingTime.setText(convertMillisToMinutesSeconds(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                resetTimer();
            }
        }.start();

        animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 500);
        animation.setDuration(timeInMillis);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            timeInMillis = DEFAULT_TIMER_TIME;
            animation.cancel();
            progressBar.setProgress(0);
            tvRemainingTime.setText(convertMillisToMinutesSeconds(timeInMillis));
        }
    }

    public static String convertMillisToMinutesSeconds(long millis) {
        // Convert milliseconds to seconds
        long seconds = millis / 1000;

        // Calculate minutes and remaining seconds
        long minutes = seconds / 60;
        seconds = seconds % 60;

        // Format the minutes and seconds as a string
        String minutesStr = String.format("%02d", minutes);
        String secondsStr = String.format("%02d", seconds);
        return minutesStr + ":" + secondsStr;
    }
}