package com.example.fimostudyplanner.notificationsprofile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fimostudyplanner.R;
import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.notificationsprofile.NotificationManager;


public class SettingsActivity extends AppCompatActivity {

    private Switch notificationsSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Mendapatkan referensi ke Switch
        notificationsSwitch = findViewById(R.id.notificationsSwitch);

        // Menambahkan listener ke Switch untuk menangani perubahan status
        notificationsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Mengatur teks TextView berdasarkan status Switch
                TextView notificationsTextView = findViewById(R.id.notificationsTextView);
                notificationsTextView.setText(isChecked ? "Notifications ON" : "Notifications OFF");

                // Panggil metode untuk menangani notifikasi
                handleNotification(isChecked);
            }
        });
    }

    private void handleNotification(boolean enableNotification) {
        // Mendapatkan instance NotificationManager dari aplikasi
        @SuppressLint("ServiceCast") NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Ambil data dari antarmuka pengguna atau sumber input lainnya

        TextView tasktitle = findViewById(R.id.taskTitleTV);
        TextView taskdescription = findViewById(R.id.taskDescTV);

        String title = tasktitle.getText().toString();
        String description = taskdescription.getText().toString();
        long dueDate = 0; // Sesuaikan dengan cara Anda mendapatkan dueDate
        boolean isCompleted = false;

        Task task = new Task(title, description, dueDate, 0, isCompleted);

        // Panggil metode untuk menjadwalkan notifikasi
        notificationManager.scheduleTaskNotification(task, enableNotification);

        // Jika notifikasi diaktifkan, buka NotificationActivity
        if (enableNotification) {
            Intent notificationIntent = new Intent(this, NotificationActivity.class);
            startActivity(notificationIntent);
        }
    }

}

