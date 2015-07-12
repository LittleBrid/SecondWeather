package com.example.zu.secondweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.zu.secondweather.service.AutoUpdateService;

/**
 * Created by zu on 2015/7/12.
 */
public class AutoUpdateReceiver extends BroadcastReceiver
{
    public void onReceive(Context context,Intent intent)
    {
        Intent i=new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
