
package com.example.myapplication.forNotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class MyFirebaseService : FirebaseMessagingService() {

    var ADMIN_ID = "admin_id"

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e("NEW_TOKEN",p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        val intent = Intent(this, FirebaseNotificationDemo::class.java)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random().nextInt(30000)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setUpChannelNotification(notificationManager)
        }

        val largeIcon = BitmapFactory.decodeResource(
            resources,
            R.drawable.ic_grain_black_24dp
        )

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this,ADMIN_ID)
            .setSmallIcon(R.drawable.ic_delete_black_24dp)
            .setLargeIcon(largeIcon)
            .setContentTitle(p0?.data?.get("title"))
            .setContentText(p0?.data?.get("message"))
            .setAutoCancel(true)
            .setSound(notificationSoundUri)
            .setContentIntent(pendingIntent)




        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.color = resources.getColor(R.color.background_dark)
        }*/


        notificationManager.notify(notificationId,notificationBuilder.build())
    }


    private fun setUpChannelNotification(manager: NotificationManager) {
        val adminName = "New Notification"
        val adminDescription = "Device to device notification"

        val adminchannel : NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            adminchannel = NotificationChannel(ADMIN_ID, adminName, NotificationManager.IMPORTANCE_HIGH)

            adminchannel.description = adminDescription

            adminchannel.enableLights(true)
            adminchannel.lightColor = Color.RED
            adminchannel.enableVibration(true)


            manager.createNotificationChannel(adminchannel)
        }
    }

}

