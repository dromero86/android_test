package com.agaromba.demo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import java.util.Random;

/**
 * Created by Daniel on 12/02/2017.
 */

public class Notify
{
    public Context ctx =  null;

    public void Notificar(long futureInMillis, String Titulo, String Detalle)
    {
        Random r = new Random();
        int notificationId = r.nextInt(1800 - 200) + 1800;

        Notification.Builder mBuilder = new Notification.Builder(ctx).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(Titulo).setContentText(Detalle);

        Intent intent  =  new Intent(ctx, MainActivity.class);
        PendingIntent activity = PendingIntent.getActivity(ctx, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(activity);

        Notification notification = mBuilder.build();

        Intent notificationIntent = new Intent(ctx, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);

    }
}
