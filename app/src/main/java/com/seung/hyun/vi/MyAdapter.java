package com.seung.hyun.vi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-04.
 */

public class MyAdapter extends RecyclerView.Adapter{
    ArrayList<Info> mList;
    Context mContext;
    LayoutInflater mInflater;
    public MyAdapter(ArrayList<Info> mList,Context mContext){
        this.mContext=mContext;
        this.mList=mList;
        mInflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_third,null);
        Myholder myholder=new Myholder(view);
        return myholder;
    }

    /**
     * 渲染ViewHolder上面View的内容
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Myholder myholder= (Myholder) holder;
        Info info=mList.get(position);
        myholder.mTvName.setText(info.name);
        myholder.mIvCheck.setSelected(info.isCheck);
        myholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position,myholder);
            }
        });
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    OnItemClickListener onItemClickListener;
    public interface  OnItemClickListener{
        void onItemClick(int position,Myholder myholder);
    }
    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public class Myholder extends RecyclerView.ViewHolder{
        TextView mTvName;
        ImageView mIvImg;
        ImageView mIvCheck;
        public Myholder(View itemView) {
            super(itemView);
            mIvCheck= (ImageView) itemView.findViewById(R.id.iv_item_check);
            mTvName= (TextView) itemView.findViewById(R.id.tv_item_name);
            mIvImg= (ImageView) itemView.findViewById(R.id.iv_item_photo);
        }
    }
}
