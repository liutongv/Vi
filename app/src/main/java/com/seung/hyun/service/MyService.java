package com.seung.hyun.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017-05-09.
 */

public class MyService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MyService", "onBind: ");
        MyBinder myBinder=new MyBinder();
        return myBinder;
    }
    public class MyBinder extends Binder{

    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService", "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, final int startId) {
        super.onStart(intent, startId);
        Log.e("MyService", "onStart: "+startId);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf();//进行自杀
//                stopSelf(startId);
            }
        }).start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("MyService", "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("MyService", "onRebind: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService", "onDestroy: ");
    }
}
