package com.example.fimostudyplanner;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PomodoroFragment extends AppCompatActivity {
    private ProgressBar circleProgressBar;
    private CountDownTimer countDownTimer;
    private long timeInMillis;

    public void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pomodoro);

        circleProgressBar = findViewById(R.id.circleProgressBar);

        Button startButton = findViewById(R.id.startButton);
        Button resetButton = findViewById(R.id.resetButton);
        Button settingsButton = findViewById(R.id.settingsButton);

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
        // Hentikan timer dan atur ulang waktu
        countDownTimer.cancel();
        // Reset tampilan dan waktu
        circleProgressBar.setProgress(100);
        timeInMillis = 0;
    }

    private void updateProgressBar(long millisUntilFinished) {
        int progress = (int) ((millisUntilFinished / (float) timeInMillis) * 100);
        circleProgressBar.setProgress(progress);
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_time_pomodoro, null);
        final EditText editTextTime = view.findViewById(R.id.editTextTime);
        Button setButton = view.findViewById(R.id.setButton);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil waktu dari EditText dan simpan
                String inputTime = editTextTime.getText().toString();
                if (!inputTime.isEmpty()) {
                    timeInMillis = Long.parseLong(inputTime) * 1000 * 60; // Menit ke milidetik
                    resetTimer();
                }
            }
        });

        builder.setView(view)
                .setTitle("Set Time")
                .setNegativeButton("Cancel", null)
                .create().show();
    }
}