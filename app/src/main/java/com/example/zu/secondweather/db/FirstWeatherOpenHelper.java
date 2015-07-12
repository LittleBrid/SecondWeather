package com.example.zu.secondweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zu on 2015/7/9.
 */
public class FirstWeatherOpenHelper extends SQLiteOpenHelper
{
    //the sql order that create a province table
    public static final String CREATE_PROVINCE="create table Province ("
            +"id integer primary key autoincrement,"
            +"province_name text,"
            +"province_code text)";
    //the sql order that creates a city table
    public static final String CREATE_CITY="create table City ("
            +"id integer primary key autoincrement,"
            +"city_name text,"
            +"city_code text,"
            +"province_id integer)";
    //the sql order that creates a county table
    public static final String CREATE_COUNTY="create table County ("
            +"id integer primary key autoincrement,"
            +"county_name text,"
            +"county_code text,"
            +"city_id integer)";

    public FirstWeatherOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {

    }

}
