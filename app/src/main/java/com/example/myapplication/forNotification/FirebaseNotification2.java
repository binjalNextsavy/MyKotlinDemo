package com.example.myapplication.forNotification;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class FirebaseNotification2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_notification2);

        Intent i = new Intent(this,FirebaseNotificationDemo.class);

       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }*/
      //  MyNotificationManager.getInstance(this).displayNotification("Greetings", "Hello how are you?");
        //MyNotificationManager.getInstance(this).showNotification(this,"Greetings", "Hello how are you?",i);
        MyNotificationManager.getInstance(this).showNotification(this,"Greetings", "Hello how are you?",i);
       // startActivity(i);
    }
}
