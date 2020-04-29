package com.ckkj.router;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.ckkj.router.aidl.IMobile;
import com.ckkj.router.service.BinderService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ServiceConnection mConn;
    private ServiceConnection mBinderConn;

    private WebView mWebView;

    private Messenger messengerReply = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("wh", "client::what:" + msg.what + ",arg:" + msg.arg1);
            super.handleMessage(msg);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = findViewById(R.id.iv);

        mWebView = findViewById(R.id.wv);
        mWebView.setOnTouchListener(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        drawable.setCircular(true);
        iv.setImageDrawable(drawable);

        bind();
    }

    private void bind() {
        mConn = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("wh", "client::message server connect");
                // 获取到服务端的IBinder，通过Messenger向服务端发送消息
                Messenger messenger = new Messenger(service);
                Message message = Message.obtain();
                message.what = 10010;
                message.arg1 = 10086;
                // 设置接收服务端回复的Messenger对象
                message.replyTo = messengerReply;
                try {
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        mBinderConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("wh", "client::bind server connect:::" + service.getClass().getName());
                try {
                    IMobile iMobile = IMobile.Stub.asInterface(service);
                    List<String> strings = iMobile.fetchMobileList();
                    Log.e("wh", "client::bind server fetchMobileList:" + strings.toString());
                    iMobile.createMobile("APPLE");
                    List<String> list = iMobile.fetchMobileList();
                    Log.e("wh", "client::bind server fetchMobileList after create one:" + list.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("wh", "client::onServiceDisconnected:" + Thread.currentThread().getName());
            }

            @Override
            public void onBindingDied(ComponentName name) {
                Log.e("wh", "client::onBindingDied:" + Thread.currentThread().getName());
            }
        };
//        Intent intent = new Intent(this, MessageService.class);
        Intent intent = new Intent(this, BinderService.class);
//        startService(intent);
//        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
        bindService(intent, mBinderConn, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(mConn);
        unbindService(mBinderConn);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e("wh", "x:" + event.getX() + ",y:" + event.getY());
        return false;
    }
}