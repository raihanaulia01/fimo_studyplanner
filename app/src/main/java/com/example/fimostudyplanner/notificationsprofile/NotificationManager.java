package com.example.fimostudyplanner.notificationsprofile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fimostudyplanner.R;
import com.example.fimostudyplanner.TaskData.Task;
import com.example.fimostudyplanner.TaskData.TaskManager;
import com.example.fimostudyplanner.notificationsprofile.NotificationHelper;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationManager extends Activity {
    private Timer timer;
    private TaskManager taskManager;
    private NotificationHelper notificationHelper;
    private Context context;

    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;

    public NotificationManager(TaskManager taskManager, NotificationHelper notificationHelper, Context context) {
        this.taskManager = taskManager;
        this.notificationHelper = notificationHelper;
        this.context = context;
        timer = new Timer();
    }

    public void scheduleTaskNotification(Task task, boolean enableNotification) {
        taskManager.addTask(task);
        taskManager.saveTasks(taskManager.getTasks());

        timer.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                sendTaskNotification(task, enableNotification);
            }
        }, Long.parseLong(String.valueOf(task.getDueDate())));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendTaskNotification(Task task, boolean enableNotification) {
        if (enableNotification) {
            int unfinishedTasks = countUnfinishedTasks();
            String message = "You have " + unfinishedTasks + " task" + (unfinishedTasks != 1 ? "s" : "") + " today";

            // Mengonfigurasi dan menampilkan notifikasi
            configureAndShowNotification(message);
        }
    }

    private void configureAndShowNotification(String message) {
        // Mendapatkan NotificationManagerCompat agar kompatibel dengan semua versi Android
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // Membuat builder untuk notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationHelper.channel_id)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("My Notification")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // Implementasikan metode onRequestPermissionsResult
        // Menampilkan notifikasi
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Meminta izin untuk POST_NOTIFICATIONS
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
            } else {
                // Jika izin sudah diberikan, tampilkan notifikasi
                notificationManager.notify(notificationHelper.notificationId, builder.build());
            }
        } else {
            // For versions lower than Oreo, you don't need permission, so you can directly show notification
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.notify(notificationHelper.notificationId, builder.build());
            }
        }

    }

    private Context requireContext() {
        return null;
    }

    private Activity requireActivity() {
        return null;
    }

    private int countUnfinishedTasks() {
        List<Task> tasks = taskManager.getTasks();
        int count = 0;

        for (Task task : tasks) {
            if (!task.isCompleted()) {
                count++;
            }
        }

        return count;
    }

    // Metode lain di sini...

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
