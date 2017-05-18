package com.seung.hyun.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017-05-11.
 */

public class MyDatabase {
    SQLiteDatabase db;
    public MyDatabase(Context mContext){
        MyOpenHelper openHelper=new MyOpenHelper(mContext);
        db=openHelper.getReadableDatabase();
    }

    /**
     * 插入
     */
    public void insert(){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name","名取");
        contentValues.put("age",22);
        db.insert("user",null,contentValues);
    }

}
