package com.example.caimogu.datastore;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private MyDBHelper myDBHelper;
    private EditText deleId;
    private EditText name;

    
    public void createSQLite(View v)//创建数据库
    {
        myDBHelper = new MyDBHelper(this,"test.db",null,1);
        myDBHelper.getWritableDatabase();
        Toast.makeText(MainActivity.this, "创建数据库成功！", Toast.LENGTH_SHORT).show();

    }


    public void saveDataToSQL(View v)//存数据--数据库
    {


        SQLiteDatabase db = myDBHelper.getWritableDatabase();

        String sql = "insert into student(id,name) values(null,?)";
        name = (EditText)findViewById(R.id.userName);
        String str = name.getText().toString();
        db.execSQL(sql,new String[]{str});
        db.close();
        Toast.makeText(MainActivity.this, "存入数据库成功！", Toast.LENGTH_SHORT).show();

    }
    public void getDataFromSQL(View v)//取数据--数据库
    {


        SQLiteDatabase db = myDBHelper.getWritableDatabase();
//      第一种查询数据的方法
        Cursor c = db.query("student",null,null,null,null,null,null);
        if(c.moveToFirst())
        {
            do{
                int id = c.getInt(c.getColumnIndex("id"));
                String name = c.getString(c.getColumnIndex("name"));

                Toast.makeText(MainActivity.this, "id:"+id+"cname:"+name, Toast.LENGTH_SHORT).show();

            }while(c.moveToNext());
        }
        c.close();

//         第二种查询数据的方法
//        String sql = "select * from student where id>?";
//        Cursor c = db.rawQuery(sql,new String[]{"1"});
//        while(c.moveToNext()){
//
//            int ids = c.getColumnIndex("id");//——返回指定列的名称，如果不存在返回-1
//            int id = c.getInt(ids);
//            String name = c.getString(1);
//            Toast.makeText(MainActivity.this, "id:"+id+"name:"+name, Toast.LENGTH_SHORT).show();
//        }
 //       c.close();

    }
    public void deleteDataFromSQL(View view)//删除数据--数据库
    {
        deleId = (EditText)findViewById(R.id.deleteId);
        String str = deleId.getText().toString();

        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        Log.i("Caimogu","第: "+str+" 条记录被删除");
        db.delete("student", "id = ?", new String[]{str});
        Toast.makeText(MainActivity.this, "删除了一条数据", Toast.LENGTH_SHORT).show();
    }


    public void saveData(View v)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", "Tom");
        editor.putInt("age", 20);
        Set<String> set = new HashSet<String>();
        set.add("JavaEE");
        set.add("Database");
        editor.putStringSet("course", set);
        editor.commit();//提交
        Toast.makeText(MainActivity.this, "保存数据成功！", Toast.LENGTH_SHORT).show();

    }
    public void getData(View v)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sp.getString("name",null);
        int age = sp.getInt("age",0);
        //Toast.makeText(MainActivity.this, name+age, Toast.LENGTH_SHORT).show();
        Set<String> set = sp.getStringSet("course",null);
        for(String s:set)
        {
            Toast.makeText(MainActivity.this, "name:"+name+"age:"+age+"course:"+s, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
