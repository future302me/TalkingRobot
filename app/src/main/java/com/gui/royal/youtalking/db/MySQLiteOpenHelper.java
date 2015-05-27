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
    private static final String CREATE_TEXTS = "create table Texts ("
                                                +"id integer primary key autoincrement,"
                                                +"code text,"
                                                +"content text)";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
