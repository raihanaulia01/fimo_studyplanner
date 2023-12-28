package com.example.fimostudyplanner;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fimostudyplanner.TaskData.Task;


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
        String title = tasktitle.toString();
        String description = taskdescription.toString();
        String id = taskid();  // Metode untuk menghasilkan ID tugas unik

        Task task = new Task(title, description, id, 0, false);

        // Panggil metode untuk menjadwalkan notifikasi
        notificationManager.scheduleTaskNotification(task, enableNotification);
    }

}
