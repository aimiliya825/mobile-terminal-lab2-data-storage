package com.example.datastorage;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private CpDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new CpDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(CpDbHelper.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.update(CpDbHelper.TABLE_NAME, values, selection, selectionArgs);
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert(CpDbHelper.TABLE_NAME, null, contentValues);
        if (rowId > 0) {
            Uri rowUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(rowUri, null);
            return rowUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CpDbHelper.TABLE_NAME, null);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
}
