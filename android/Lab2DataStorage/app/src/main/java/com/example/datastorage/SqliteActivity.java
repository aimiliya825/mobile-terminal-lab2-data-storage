package com.example.datastorage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteActivity extends Activity {

    private DBManager mgr;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        listView = (ListView) findViewById(R.id.listView);
        // 在 Activity 的 onCreate 里实例化 DBManager，保证 context 已初始化
        mgr = new DBManager(this);
        showData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 应用的最后一个 Activity 关闭时应释放数据库
        mgr.closeDB();
    }

    /** add：一次插入 4 条记录 */
    public void add(View view) {
        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(new Person("A", 10, "Student A"));
        persons.add(new Person("B", 20, "Student B"));
        persons.add(new Person("C", 30, "Student C"));
        persons.add(new Person("D", 40, "Student D"));
        mgr.add(persons);
        showData();
    }

    /** update：把 D 的年龄修改为 80 */
    public void update(View view) {
        Person person = new Person();
        person.name = "D";
        person.age = 80;
        mgr.updateAge(person);
        showData();
    }

    /** delete：删除年龄大于等于 30 的记录（C、D） */
    public void delete(View view) {
        Person person = new Person();
        person.age = 30;
        mgr.deleteOldPerson(person);
        showData();
    }

    /** query：查询并显示全部记录 */
    public void query(View view) {
        showData();
    }

    private void showData() {
        List<Person> persons = mgr.query();
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Person person : persons) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", person.name);
            map.put("info", person.age + " years old, " + person.info);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2,
                new String[]{"name", "info"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);
    }
}
