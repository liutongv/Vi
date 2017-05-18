package com.seung.hyun.vi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        button= (Button) findViewById(R.id.btn_pop);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        PopupWindow popupWindow=new PopupWindow();
        View view= LayoutInflater.from(this).inflate(R.layout.activity_popwindow,null);
        //设置展示的内容
        popupWindow.setContentView(view);
        popupWindow.setWidth(500);
        popupWindow.setHeight(500);
        //设置外部是否可点击
        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAsDropDown(button);
        popupWindow.showAtLocation(view, Gravity.CENTER,200,200);
    }

}
