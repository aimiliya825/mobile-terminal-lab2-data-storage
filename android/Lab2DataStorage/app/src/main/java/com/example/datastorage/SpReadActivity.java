package com.example.datastorage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SpReadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_read); // 设置布局文件
        TextView usernameTV = (TextView) findViewById(R.id.username);
        TextView passwordTV = (TextView) findViewById(R.id.password);
        // 获得私有类型的 SharedPreferences
        SharedPreferences sp = getSharedPreferences("mrsfot", MODE_PRIVATE);
        String username = sp.getString("username", "mr");   // 获得用户名
        String password = sp.getString("password", "001");  // 获得密码
        usernameTV.setText("用户名：" + username);            // 显示用户名
        passwordTV.setText("密    码：" + password);          // 显示密码
    }
}
