package com.example.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmReceiver", "Alarm received!");

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(4000);

        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        // setting default ringtone
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);

        // play ringtone
        ringtone.play();


        // Extract information from the intent
        String message = intent.getStringExtra("message");
        int year = intent.getIntExtra("year", 0);
        int month = intent.getIntExtra("month", 0);
        int day = intent.getIntExtra("day", 0);
        int hour = intent.getIntExtra("hour", 0);
        int minute = intent.getIntExtra("minute", 0);


        // Implement the logic to trigger the alarm (e.g., play a sound or show a notification)
        // For simplicity, let's show a notification
        NotificationUtils.showNotification(context, "Alarm Triggered", getFormattedDateTime(year, month, day, hour, minute) + "\n" + message);
    }

    private String getFormattedDateTime(int year, int month, int day, int hour, int minute) {
        // Format the date and time as needed
        return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
    }
}
