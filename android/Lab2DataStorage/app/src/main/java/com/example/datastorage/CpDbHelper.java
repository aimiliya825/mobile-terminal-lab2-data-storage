package com.example.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CpDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Users.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "User";

    public CpDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, USER_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
