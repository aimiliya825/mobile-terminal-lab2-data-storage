package com.example.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SpMainActivity extends Activity {

    private EditText usernameET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_main); // 应用自定义布局文件
        usernameET = (EditText) findViewById(R.id.username); // 获得用户名控件
        passwordET = (EditText) findViewById(R.id.password); // 获得密码控件
        Button login = (Button) findViewById(R.id.login);    // 获得按钮控件

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString(); // 获得用户名
                String password = passwordET.getText().toString(); // 获得密码
                // 获得私有类型的 SharedPreferences
                SharedPreferences sp = getSharedPreferences("mrsfot", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();       // 获得 Editor 对象
                editor.putString("username", username);            // 增加用户名
                editor.putString("password", password);            // 增加密码
                editor.commit();                                   // 确认提交
                Intent intent = new Intent(SpMainActivity.this, SpReadActivity.class);
                startActivity(intent);                             // 实现跳转
            }
        });
    }
}
