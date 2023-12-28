package com.example.fimostudyplanner;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

@RequiresApi(api = Build.VERSION_CODES.O)
public class NotificationHelper {

    public static final String channel_id = "channel_id";
    private static int notificationIdTask;
    private static int notificationIdPomodoro;
    private static int notificationIdQuote;


    private final Context context;
    public int notificationId;

    public NotificationHelper(Context context) {
        this.context = context;
    }

    public void sendTaskNotification(String message) {
        NotificationCompat.Builder builder = createNotificationBuilder("Task Notification")
                .setContentText(message);

        showNotification(builder, NotificationHelper.notificationIdTask);
    }

    public void sendPomodoroNotification() {
        NotificationCompat.Builder builder = createNotificationBuilder("Pomodoro Notification")
                .setContentText("Great job! You completed a Pomodoro session.");

        showNotification(builder, NotificationHelper.notificationIdPomodoro);
    }

    public void sendQuoteNotification() {
        createNotificationChannel();

        String randomQuote = QuoteApi.getRandomQuote();

        if (randomQuote != null) {
            NotificationCompat.Builder builder = createNotificationBuilder("Quote Notification")
                    .setContentText("Words of the Day: " + randomQuote);

            showNotification(builder, NotificationHelper.notificationIdQuote);
        }
    }

    private NotificationCompat.Builder createNotificationBuilder(String title) {
        return new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle(title)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
    }

    private void showNotification(NotificationCompat.Builder builder, int notificationId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Create a channel on devices running Android 8.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "App Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(notificationId, builder.build());
    }

        public void createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "App Notifications";
                String description = "Channel for App Notifications";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel("channel_id", name, importance);
                channel.setDescription(description);

                NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }
}

