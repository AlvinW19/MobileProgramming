package com.example.newsreport;

import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

public class PushService extends HmsMessageService {
    private static final String TAG = "PushService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG,"onMessageReceived");
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG,"onNewToken"+s);
    }
}
