package com.ctis487.motherzilla;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends  SQLiteOpenHelper {
    public static String DATABASE_NAME= "motherzilla";
    public static int DATABASE_VERSION = 2;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table if not exists profiles (id integer primary key autoincrement, age integer, name text, description text, speciality text, img blob)";
        sqLiteDatabase.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String qry="DROP TABLE IF EXISTS profiles";
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);
    }

    public Cursor readAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        String qry="select * from profiles order by id desc";

        Cursor cursor = db.rawQuery(qry, null);


        return cursor;

    }


}
