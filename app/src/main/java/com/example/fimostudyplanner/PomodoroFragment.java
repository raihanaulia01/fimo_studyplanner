package com.example.fimostudyplanner;

import android.app.AlertDialog;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PomodoroFragment extends Fragment {

    private ProgressBar circleProgressBar;
    private CountDownTimer countDownTimer;
    private long timeInMillis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pomodoro, container, false);

        circleProgressBar = view.findViewById(R.id.circleProgressBar);

        Button startButton = view.findViewById(R.id.startButton);
        Button resetButton = view.findViewById(R.id.resetButton);
        Button settingsButton = view.findViewById(R.id.settingsButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingsDialog();
            }
        });

        return view;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateProgressBar(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                resetTimer();
            }
        }.start();
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
            circleProgressBar.setProgress(100);
            timeInMillis = 0;
        }
    }

    private void updateProgressBar(long millisUntilFinished) {
        int progress = (int) ((millisUntilFinished / (float) timeInMillis) * 100);
        circleProgressBar.setProgress(progress);
    }

    private void showSettingsDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.add_time_pomodoro, null);
        final EditText editTextTime = dialogView.findViewById(R.id.editTextTime);
        Button setButton = dialogView.findViewById(R.id.setButton);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTime = editTextTime.getText().toString();
                if (!inputTime.isEmpty()) {
                    timeInMillis = Long.parseLong(inputTime) * 1000 * 60; // Menit ke milidetik
                    resetTimer();
                    alertDialog.dismiss();
                }
            }
        });

        alertDialog.show();
    }
}