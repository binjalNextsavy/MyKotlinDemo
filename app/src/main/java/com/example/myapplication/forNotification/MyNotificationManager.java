package com.example.myapplication.forNotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.example.myapplication.utility.Constants;
import com.example.myapplication.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyNotificationManager {

    private Context mCtx;
    private static MyNotificationManager mInstance;

    private MyNotificationManager(Context context) {
        mCtx = context;
    }

    public static synchronized MyNotificationManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_delete_black_24dp)
                        .setContentTitle(title)
                        .setContentText(body);




        Intent resultIntent = new Intent(mCtx, FirebaseNotification2.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);



        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);


        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }

    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = Constants.CHANNEL_ID;
        String channelName = Constants.CHANNEL_NAME;
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_delete_black_24dp)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }


}
