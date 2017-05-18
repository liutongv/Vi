package com.seung.hyun.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017-05-11.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public static final String SQL_NAME="name";
    public static final int VERSION=1;
    public MyOpenHelper(Context context) {
        super(context, SQL_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user(age integer,name text)";
        db.execSQL(sql);
        String sql2="create table student(age integer,name text)";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
