package com.example.zu.secondweather.model;

/**
 * Created by zu on 2015/7/9.
 */
public class County extends Province
{
    private int cityId;
    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int id)
    {
        cityId=id;
    }
}
