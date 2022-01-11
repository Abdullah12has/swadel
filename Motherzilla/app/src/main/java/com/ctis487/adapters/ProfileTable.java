package com.ctis487.adapters;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ProfileTable {

    public static final String TABLE_NAME="profiles";
    public static final String FIELD_ID="id";
    public static final String FIELD_NAME ="name";
    public static final String FIELD_AGE="age";
    public static final String FIELD_SPECIALITY="speciality";
    public static final String FIELD_DESCRIPTION="description";
    public static final String FIELD_IMAGE="image";
    public static final String CREATE_TABLE = "CREATE TABLE  " + TABLE_NAME  +" (" + FIELD_ID  +" INTEGER," + FIELD_NAME +" TEXT NOT NULL," + FIELD_AGE  +" INTEGER NOT NULL," + FIELD_SPECIALITY +" TEXT NOT NULL," + FIELD_DESCRIPTION  + " TEXT NOT NULL," + FIELD_IMAGE + " BLOB,"  + " PRIMARY KEY( " + FIELD_ID  +" AUTOINCREMENT)" + ");";
    public static final String DROP_TABLE = "DROP TABLE if exists " + TABLE_NAME;


    public static List<Profile> getAll(DatabaseHelper db){

        Cursor cursor = db.getAllRecords(TABLE_NAME, null);
        List<Profile> data=new ArrayList<Profile>();
        Profile profile = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String speciality = cursor.getString(3);
            String description = cursor.getString(4);
            byte[] image = cursor.getBlob(5);


            profile = new Profile(id,name, age,speciality,description, image);
            data.add(profile);
        }
        return data;
    }

    public static List<Profile> find(DatabaseHelper db, String key){

        String where = FIELD_NAME +" like '%"+key+"%'";
        Cursor cursor = db.getSomeRecords(TABLE_NAME,null, where);
        List<Profile> data = new ArrayList<>();
        Profile profile = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String speciality = cursor.getString(3);
            String description = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            profile = new Profile(id,name, age,speciality,description, image);
            data.add(profile) ;
        }
        return data;
    }


    public static boolean delete(DatabaseHelper db, int id){
        String where = FIELD_ID +" = "+ id;
        boolean res = db.delete(TABLE_NAME,where);
        return res;
    }
}
