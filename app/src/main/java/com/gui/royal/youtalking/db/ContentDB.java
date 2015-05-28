package com.gui.royal.youtalking.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gui.royal.youtalking.model.SendTexts;
import com.gui.royal.youtalking.model.Texts;

/**
 * Created by Jeremy on 2015/5/27.
 * 聊天数据库类，封装了存取聊天内容的操作
 */
public class ContentDB {

    /**
     * DB name
     */
    public static final String DB_NAME = "contents";

    /**
     * DB Version
     */
    public static final int VERSION = 1;

    private static ContentDB contentDB;

    private SQLiteDatabase db;

    /**
     * 单例模式
     * 私有化构造法
     * 保证同时只有一个实例操作数据库
     */
    private ContentDB (Context context) {
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase(); //获取数据库实例
    }

    /**
     * 提供数据实例方法
     */
    public synchronized static ContentDB getInstance (Context context) {
        if (contentDB == null) {
            contentDB = new ContentDB(context);
        }
        return contentDB;
    }

    /**
     * 存储解析出来的数据
     */
    public void saveReceivedTexts (Texts texts){
        if (texts != null) {
            ContentValues values = new ContentValues();
            values.put("code", texts.getCode());
            values.put("content", texts.getContent());
            db.insert("ReceivedTexts", null, values);
        }
    }

    /**
     * 存储发送的数据
     * @param sendTexts 发送的数据
     */
    public void saveSendTexts (SendTexts sendTexts) {
        if (sendTexts != null) {
            ContentValues values = new ContentValues();
            values.put("content", sendTexts.getContent());
            db.insert("SendTexts", null, values);
        }
    }


    //TODO 读取接受的消息
    //TODO 读取发送的消息




}
