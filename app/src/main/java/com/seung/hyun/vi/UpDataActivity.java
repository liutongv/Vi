package com.seung.hyun.vi;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Administrator on 2017-05-12.
 */

public class UpDataActivity extends Activity implements View.OnClickListener {
    MyBroadcastReceiver myBroadcastReceiver;
    long downId;//下载的ID
    DownloadManager manager;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
    }

    @Override
    public void onContentChanged() {
        button= (Button) findViewById(R.id.btn_updata);
        button.setOnClickListener(this);
        myBroadcastReceiver=new MyBroadcastReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(myBroadcastReceiver,filter);
    }

    @Override
    public void onClick(View v) {
        downFile("http://172.27.35.4:8080/vi.apk");
    }

    /**
     * 下载文件
     */
    public void downFile(String downPath){
        //获取DownloadManager对象
        manager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //新建一个请求 并且指定下载链接
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(downPath));
        //对请求进行设置
        //下载时的网络类型
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
        //下载进度显示在通知栏
        request.setShowRunningNotification(true);
        //下载文件的名称  参数二:文件类型
        request.setDestinationInExternalFilesDir(this,null,"victory.apk");
        //开始下载 下载的ID
        downId=manager.enqueue(request);
        Toast.makeText(this,"开始下载",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
    public class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"下载完毕",Toast.LENGTH_SHORT).show();
            //当前下载完毕的ID
            long thisId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            if(downId==thisId){
                //通过下载的ID拿到下载的文件
                Uri uriForDownloadedFile = manager.getUriForDownloadedFile(downId);
//                String columns[]={MediaStore.Images.Media.DATA};
//                Cursor c = getContentResolver().query(uriForDownloadedFile, columns, null, null, null);
//                c.moveToFirst();
                //apk路径
//                String apkPath = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
//                Log.e("vi", "apk路径: "+apkPath);
                Intent intent1=new Intent(Intent.ACTION_VIEW);
                intent1.setDataAndType(uriForDownloadedFile,"application/vnd.android.package-archive");
                startActivity(intent1);
            }
        }
    }
}
