package com.seung.hyun.vi;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.seung.hyun.service.MyService;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017-05-09.
 */

public class TestServiceActivity extends Activity implements View.OnClickListener {
    Button mBtnStart;//启动服务
    Button mBtnStop;//停止服务
    Button mBtnBind;//绑定服务
    Button mBtnUnBind;//解绑服务
    Button mBtnShared;//分享
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @Override
    public void onContentChanged() {
        mBtnStart = (Button) findViewById(R.id.btn_start_service);
        mBtnStop = (Button) findViewById(R.id.btn_stop_service);
        mBtnBind = (Button) findViewById(R.id.btn_bind_service);
        mBtnUnBind = (Button) findViewById(R.id.btn_unbind_service);
        mBtnShared= (Button) findViewById(R.id.btn_shared);
        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnBind.setOnClickListener(this);
        mBtnUnBind.setOnClickListener(this);
        mBtnShared.setOnClickListener(this);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("vi", "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("vi", "onServiceDisconnected: ");
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        switch (v.getId()) {
            case R.id.btn_start_service://开启服务
                startService(intent);
                break;
            case R.id.btn_stop_service://停止服务
                stopService(intent);
                break;
            case R.id.btn_bind_service://绑定服务
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service://解绑服务
                unbindService(serviceConnection);
                break;
            case R.id.btn_shared://分享
                showShare();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
       //关闭sso授权
        oks.disableSSOWhenAuthorize();

       // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
       // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
       // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
       // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
       //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
       // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
       // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
       // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
       // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }
}
