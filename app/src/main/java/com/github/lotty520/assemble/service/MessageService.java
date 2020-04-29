package com.ckkj.router.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author lotty
 */
public class MessageService extends Service {

    @SuppressLint("HandlerLeak")
    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("wh", "server::what:" + msg.what + ",arg:" + msg.arg1);
            if (msg.what == 10010) {
                Message obtain = Message.obtain();
                obtain.what = 10000;
                obtain.arg1 = 10086;
                try {
                    msg.replyTo.send(obtain);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    });

    @Override
    public void onCreate() {
        Log.e("wh", "server::onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("wh", "server::onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("wh", "server::onBind");
        return messenger.getBinder();
    }
}
