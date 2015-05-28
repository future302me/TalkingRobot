package com.gui.royal.youtalking.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jeremy on 2015/5/26.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    /**
     *建表语句
     */
    private static final String CREATE_TEXTS = "create table ReceivedTexts ("
                                                +"id integer primary key autoincrement,"
                                                +"code text,"
                                                +"content text)";

    private static final String CREATE_SENDTEXTS = "create table SendTexts ("
                                                    + "id integer primary key autoincrement,"
                                                    + "content text)";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEXTS);//创建接收消息的文本类数据库
        db.execSQL(CREATE_SENDTEXTS);//创建发送消息数据库
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
