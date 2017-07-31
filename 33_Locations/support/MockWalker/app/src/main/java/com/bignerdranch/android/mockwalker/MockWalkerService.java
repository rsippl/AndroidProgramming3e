package com.bignerdranch.android.mockwalker;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class MockWalkerService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private static final int PENDING_SHUTDOWN_ID = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Intent shutdownIntent = new Intent(this, ShutdownReceiver.class);
        PendingIntent shutdownPI = PendingIntent.getBroadcast(
                this, PENDING_SHUTDOWN_ID, shutdownIntent, 0
        );
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_map)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setTicker(getString(R.string.app_name))
                .setContentIntent(shutdownPI)
                .build();

        startForeground(NOTIFICATION_ID, notification);
        MockWalker.get(this).setStarted(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        stopForeground(true);
        MockWalker.get(this).setStarted(false);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("" + getClass().getName() + " is not a bindable service");
    }


}
