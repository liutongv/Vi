package com.seung.hyun.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Administrator on 2017-05-11.
 */

public class TestIntentService extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
