package com.example.caimogu.datastore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

/**
 * Created by Caimogu on 2015/11/21.
 */
public class MyDBHelper extends SQLiteOpenHelper
{
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
}

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table student(id integer primary key,name text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exits student");
        this.onCreate(db);

    }
}
