package com.example.educlub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.showTV);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        String CHANNEL_ID = "Channel_id";
        Notification notification;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "notification name", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            ;
            //Notification notification;
            notification = new NotificationCompat.Builder(this, CHANNEL_ID).
                    setSmallIcon(R.drawable.ic_launcher_background).
                    setContentTitle("This is title").
                    setContentText("This content text").
                    build();
        } else {

            notification = new NotificationCompat.Builder(this, CHANNEL_ID).
                    setSmallIcon(R.drawable.ic_launcher_background).
                    setContentTitle("This is title").
                    setContentText("This content text").
                    build();
        }
        //manager.notify(1001,notification);


        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, AlertDialog.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        manager.notify(1001, builder.build());

//        NotificationCompat notificationCompat=new
//                NotificationManagerCompat.Builder(this,"123").
//                setSmallIcon(R.drawable.ic_launcher_background).
//                setContentTitle("This is title").
//                setContentText("This content text").
//                build();
    }


}
