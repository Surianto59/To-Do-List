package com.karimun.todolist;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.core.app.NotificationCompat;

public class TaskUtils {

    public static final int NOTIFICATION_ID = 0;
    public static final String PRIMARY_NOTIFICATION_CHANNEL_ID = "primary_notification_channel";
    public static final String TASK_TITLE_KEY = "task_title";

    // Identify current date
    public static String currentDate() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date currentDate=null;

        try {
            currentDate = simpleDateFormat.parse(String.format("%s:%s", hour, minute));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        String result =null;
        if (currentDate != null)
            result = simpleDateFormat.format(currentDate);

        return result;
    }

    // Create and send notification to the user (Not in use for now)
    public static void makeNotification(Context context, String taskTitle) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PRIMARY_NOTIFICATION_CHANNEL_ID, "Todo List Notification Channel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder= new NotificationCompat.Builder(context, PRIMARY_NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(String.format("It's time to %s", taskTitle))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    // Ensure this class is not instantiated
    private TaskUtils() {}
}
