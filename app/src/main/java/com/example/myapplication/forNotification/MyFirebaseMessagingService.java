package com.example.myapplication.forNotification;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().size() > 0){
            //handle the data message here
        }

        //getting the title and the body
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();
    }
}
