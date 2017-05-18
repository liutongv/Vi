package com.seung.hyun.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.seung.hyun.database.MyOpenHelper;

/**
 * 内容提供者
 * Created by Administrator on 2017-05-11.
 */

public class MyProvider extends ContentProvider{
    SQLiteDatabase sqLiteDatabase;
    UriMatcher uriMatcher;

    /**
     * 主要用来进行数据库操作对象的初始化
     * @return true:表示可以成功使用
     */
    @Override
    public boolean onCreate() {
        MyOpenHelper openHelper=new MyOpenHelper(getContext());
        sqLiteDatabase=openHelper.getReadableDatabase();
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        //权限 表名 匹配成功的返回结果
        uriMatcher.addURI("com.seung.hyun.vi.PROVIDER_USER","user",22);
        return true;
    }

    /**
     * 访问者通过此方法进行数据查询
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c=null;
        switch (uriMatcher.match(uri)){
            case 22:
                c=sqLiteDatabase.query("user",projection,selection,selectionArgs,null,null,sortOrder);
                break;
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long v=0;
        switch (uriMatcher.match(uri)){
            case 22:
                Log.e("vi", "提供者: 它不进这里来呀" );
                ContentValues values2=new ContentValues();
                values2.put("age",18);
                values2.put("name","的场");
                v=sqLiteDatabase.insert("user",null,values2);
                break;
        }
        return Uri.parse(uri.toString()+"/"+v);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int user=0;
        switch (uriMatcher.match(uri)){
            case 22:
                user = sqLiteDatabase.delete("user", "name=?", new String[]{"的场"});
                break;
        }
        return user;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
