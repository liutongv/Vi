package com.seung.hyun.vi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-04.
 */

public class ThirdActivity extends Activity implements View.OnClickListener, MyAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    TextView mTvFinish;  //完成
    MyAdapter adapter;  //适配器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    public void onContentChanged() {
        mTvFinish= (TextView) findViewById(R.id.tv_third_finish);
        mTvFinish.setOnClickListener(this);
        recyclerView= (RecyclerView) findViewById(R.id.rcv_third);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Info> mList=new ArrayList<>();  //数据源
        mList.add(new Info("名取"));
        mList.add(new Info("田沼"));
        mList.add(new Info("多轨"));
        mList.add(new Info("丙"));
        mList.add(new Info("三筱"));
        mList.add(new Info("中级"));
        mList.add(new Info("小狐狸"));
        adapter=new MyAdapter(mList,this);  //适配器
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_third_finish:  //完成
                StringBuilder builder=new StringBuilder();
                for(Info info:adapter.mList){
                    if(info.isCheck){
                        builder.append(info.name).append(",");
                    }
                }
                if(builder.length()>1){
                    builder.deleteCharAt(builder.length()-1);
                }
                Intent intent=new Intent();
                intent.putExtra("result",builder.toString());
                setResult(200,intent);  //返回码
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(int position, MyAdapter.Myholder myholder) {
        //改变图片的选中状态
        myholder.mIvCheck.setSelected(!myholder.mIvCheck.isSelected());
        //改变数据源
        adapter.mList.get(position).isCheck=myholder.mIvCheck.isSelected();
    }
}
