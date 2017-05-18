package com.seung.hyun.vi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seung.hyun.database.MyDatabase;
import com.unionpay.UPPayAssistEx;

/**
 * Created by Administrator on 2017-05-04.
 */

public class SecondActivity extends Activity implements View.OnClickListener {
    LinearLayout mLL;  //点击跳转到二级界面
    TextView mTvName;  //选择的人员姓名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        MyDatabase myDatabase=new MyDatabase(this);
        myDatabase.insert();
    }

    @Override
    public void onContentChanged() {
        mLL = (LinearLayout) findViewById(R.id.ll_main);
        mTvName = (TextView) findViewById(R.id.tv_nnnnnn);
        mLL.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_main:
                String serverMode = "01";
                UPPayAssistEx.startPay(this, null, null, "632995460151105445400", serverMode);
//                Intent intent=new Intent(this,ThirdActivity.class);
//                startActivityForResult(intent,10);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(data!=null) {
//            String result = data.getStringExtra("result");
//            mTvName.setText(result);
//        }
        if (data == null) {
            return;
        }
        String msg = "";
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        } else if (str.equalsIgnoreCase("fail")) {
            Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        } else if (str.equalsIgnoreCase("cancel")) {
            Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
        }
    }
}
