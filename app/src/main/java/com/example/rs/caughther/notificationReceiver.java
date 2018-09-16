package com.example.rs.caughther;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

@SuppressLint("OverrideAbstract")
public class notificationReceiver extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        final String packageName = sbn.getPackageName();
//        if (!TextUtils.isEmpty(packageName) && packageName.equals("com.facebook.katana")) {
//
//        }
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();
        Log.d("DAMN",""+title+"\n"+text);
        databaseHandler db = new databaseHandler(getApplicationContext());
        db.add(title,text);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // Nothing to do
    }
}
