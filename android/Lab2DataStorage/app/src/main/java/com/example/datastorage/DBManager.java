package com.example.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        // getWritableDatabase 内部调用 openOrCreateDatabase，必须保证 context 已初始化
        db = helper.getWritableDatabase();
    }

    /** 批量添加记录，使用事务保证原子性 */
    public void add(List<Person> persons) {
        db.beginTransaction();
        try {
            for (Person person : persons) {
                db.execSQL("INSERT INTO person VALUES(null, ?, ?, ?)",
                        new Object[]{person.name, person.age, person.info});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /** 按姓名修改年龄 */
    public void updateAge(Person person) {
        ContentValues cv = new ContentValues();
        cv.put("age", person.age);
        db.update("person", cv, "name = ?", new String[]{person.name});
    }

    /** 删除年龄大于等于指定值的记录 */
    public void deleteOldPerson(Person person) {
        db.delete("person", "age >= ?", new String[]{String.valueOf(person.age)});
    }

    /** 查询全部记录 */
    public List<Person> query() {
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Person person = new Person();
            person._id = c.getInt(c.getColumnIndex("_id"));
            person.name = c.getString(c.getColumnIndex("name"));
            person.age = c.getInt(c.getColumnIndex("age"));
            person.info = c.getString(c.getColumnIndex("info"));
            persons.add(person);
        }
        c.close();
        return persons;
    }

    public Cursor queryTheCursor() {
        return db.rawQuery("SELECT * FROM person", null);
    }

    public void closeDB() {
        db.close();
    }
}
