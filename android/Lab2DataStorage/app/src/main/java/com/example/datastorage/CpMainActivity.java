package com.example.datastorage;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CpMainActivity extends Activity {

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.datastorage.mycontentprovider");

    private TextView insertInfoTV;
    private TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp);
        insertInfoTV = (TextView) findViewById(R.id.insertInfo);
        resultTV = (TextView) findViewById(R.id.result);

        // 客户端通过 ContentResolver 向 ContentProvider 插入数据
        Uri first = insertRecord("wwh");
        Uri second = insertRecord("hujunfeng");
        insertInfoTV.setText("插入数据成功：\n" + first + "\n" + second);

        // 客户端通过 ContentResolver 查询 ContentProvider 中的数据
        displayRecords();
    }

    private Uri insertRecord(String userName) {
        ContentValues values = new ContentValues();
        values.put("USER_NAME", userName);
        ContentResolver cr = getContentResolver();
        Uri uri = cr.insert(CONTENT_URI, values);
        Toast.makeText(this, "已插入：" + userName, Toast.LENGTH_SHORT).show();
        return uri;
    }

    private void displayRecords() {
        String columns[] = new String[]{"_id", "USER_NAME"};
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(CONTENT_URI, columns, null, null, null);
        StringBuilder sb = new StringBuilder("查询结果：\n");
        if (cur != null) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex("_id"));
                String userName = cur.getString(cur.getColumnIndex("USER_NAME"));
                sb.append("_id=").append(id).append("   USER_NAME=").append(userName).append("\n");
            }
            cur.close();
        }
        resultTV.setText(sb.toString());
    }
}
