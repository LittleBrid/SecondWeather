package com.example.zu.secondweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zu.secondweather.model.City;
import com.example.zu.secondweather.model.County;
import com.example.zu.secondweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zu on 2015/7/9.
 */
//操作数据库的工具类，包括查询、插入province、city以及county
public class FirstWeatherDB
{
    public static final String DB_NAME="first_weather";
    public static final int VERSION=1;
    private static FirstWeatherDB firstWeatherDB;
    private SQLiteDatabase db;

    private FirstWeatherDB(Context context)
    {
        FirstWeatherOpenHelper dbHelper=new FirstWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }

    public synchronized static FirstWeatherDB getInstance(Context context)
    {
        if(firstWeatherDB==null)
        {
            firstWeatherDB=new FirstWeatherDB(context);

        }
        return firstWeatherDB;
    }

    public void saveProvince(Province province)
    {
        if(province!=null)
        {
            ContentValues values=new ContentValues();
            values.put("province_name",province.getName());
            values.put("province_code",province.getCode());
            db.insert("Province",null,values);
        }
    }

    public List<Province> loadProvinces()
    {
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do
            {
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while(cursor.moveToNext());

        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }

    public void saveCity(City city)
    {
        if(city!=null)
        {
            ContentValues values=new ContentValues();
            values.put("city_name",city.getName());
            values.put("city_code",city.getCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    public List<City> loadCities(int provinceId)
    {
        List<City> list = new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id=?",new String[]{String.valueOf(provinceId)},null,null,null);
        if(cursor.moveToFirst())
        {
            do {
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while(cursor.moveToNext());

        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }

    public void saveCounty(County county)
    {
        if(county!=null)
        {
            ContentValues values=new ContentValues();
            values.put("county_name",county.getName());
            values.put("county_code",county.getCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }

    }

    public List<County> loadCounties(int cityId)
    {
        List<County> list=new ArrayList<County>();
        Cursor cursor=db.query("County",null,"city_id=?",new String[]{String.valueOf(cityId)},null,null,null);
        if(cursor.moveToFirst())
        {
            do
            {
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCityId(cityId);
                list.add(county);
            }while(cursor.moveToNext());
        }
        if(cursor!=null)
        {
            cursor.close();
        }
        return list;
    }
}
