package com.example.educlub.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.educlub.App;
import com.example.educlub.R;
import com.example.educlub.interfaces.IAPIMethods;
import com.example.educlub.network.RetrofitClientInstance;
import com.example.educlub.pojo.UserInfo;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FCMService extends FirebaseMessagingService {
    public static final String TAG="tag";
    public FCMService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            showNotification(remoteMessage.getNotification());
        }
    }

    private void showNotification(RemoteMessage.Notification remoteNotification){
        String CHANNEL_ID = "Channel_id";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "EduClub", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).
                    setSmallIcon(R.mipmap.logo2).
                    setContentTitle(remoteNotification.getTitle()).
                    setContentText(remoteNotification.getBody()).
                    build();
            manager.notify(1001,notification);
        } else {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).
                    setSmallIcon(R.drawable.ic_launcher_background).
                    setContentTitle(remoteNotification.getTitle()).
                    setContentText(remoteNotification.getBody()).
                    build();
            manager.notify(1001,notification);
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        IAPIMethods service = RetrofitClientInstance.getRetrofitInstance().create(IAPIMethods.class);
        UserInfo userInfo= App.getInstance().getUserInfo();
        if(userInfo!=null){
            Call<UserInfo> call = service.sendFireBaseToken(userInfo.getToken(),token);
            call.enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    if(response.isSuccessful()){
                        changeUserInfo(response.body());
                    }
                    else {
                        //TODO Firebase token refresh error
                        try {
                            Log.d(TAG,response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    //TODO Firebase token refresh error
                    try {
                        Log.d(TAG,t.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    private void changeUserInfo(UserInfo userInfo){
        App app=App.getInstance();
        app.setUserInfo(userInfo);
    }


}