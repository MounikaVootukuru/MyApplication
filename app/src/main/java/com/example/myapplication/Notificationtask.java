package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class Notificationtask extends AppCompatActivity {
    private NotificationCompat.Builder mNotifyBuilder;

    private NotificationManager mNotifyManager;

    private static final int NOTIFICATION_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationtask);

        mNotifyManager = (NotificationManager)

                getSystemService(NOTIFICATION_SERVICE);

        createNotificationChannel();


    }

    public void handleClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                PendingIntent notifyPendingIntent = null;
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channelid")

                        .setSmallIcon(R.drawable.ic_launcher_background)

                        .setContentTitle("whatsapp")

                        .setContentText("hii")
                        .addAction(R.drawable.ic_launcher_foreground, getString(R.string.enter_password), notifyPendingIntent)

                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                mNotifyManager.notify(NOTIFICATION_ID, builder.build());
                break;
            case R.id.button2:
                Intent dialIntent = new Intent(Notificationtask.this, MainActivity.class);
                notifyPendingIntent = PendingIntent.getActivity(this, 007, dialIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager =
                        (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setInexactRepeating(
                        AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        SystemClock.elapsedRealtime() + 10 * 1000,//trigger time
                        AlarmManager.INTERVAL_FIFTEEN_MINUTES, // repeating interval
                        notifyPendingIntent);

                break;
        }




       /* mNotifyBuilder = new NotificationCompat.Builder(this)

                .setContentTitle("Notification title")

                .setContentText(" notification content.")

                .setSmallIcon(android.R.drawable.stat_notify_chat);
        Notification myNotification = mNotifyBuilder.build();

        mNotifyManager.notify(NOTIFICATION_ID,  myNotification);*/



    }



    private void createNotificationChannel() {

        // Create the NotificationChannel, but only on API 26+ because

        // the NotificationChannel class is new and not in the support library

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "mychannel";

            //getString(R.string.channel_name);

            String description = "channel description";



            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("channelid", name, importance);

            channel.setDescription(description);



            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);

        }

    }
}
