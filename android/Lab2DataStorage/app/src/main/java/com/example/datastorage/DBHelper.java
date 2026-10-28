package com.example.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        // CursorFactory 设置为 null，使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 数据库第一次被创建时 onCreate 会被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS person ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name VARCHAR, age INTEGER, info TEXT)");
    }

    // 数据库版本发生变化时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS person");
        onCreate(db);
    }
}
