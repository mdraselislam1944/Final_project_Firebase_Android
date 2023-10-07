package com.example.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationUtils {
    private static final String CHANNEL_ID = "AlarmChannel";

    public static void showNotification(Context context, String title, String content) {
        createNotificationChannel(context);

        Notification.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(context, CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(R.drawable.ic_launcher_foreground);
        } else {
            // For versions below Oreo, you can provide a fallback or ignore the notification
            builder = null;
        }

        if (builder != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Use a unique ID for each notification
            int notificationId = generateUniqueNotificationId();

            notificationManager.notify(notificationId, builder.build());
        }
    }

    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "AlarmChannel";
            String description = "Channel for Alarm Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private static int generateUniqueNotificationId() {
        // Implement a mechanism to generate unique notification IDs
        // You could use a counter, a timestamp, or any other approach
        return (int) System.currentTimeMillis();
    }
}
