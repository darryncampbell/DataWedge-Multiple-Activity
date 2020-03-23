package com.zebra.dwmultiactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(DataWedgeInterface.ACTION_RESULT_DATAWEDGE_FROM_6_2))
        {
            if (intent.hasExtra(DataWedgeInterface.EXTRA_RESULT_GET_ACTIVE_PROFILE))
            {
                //  6.2 API to GetActiveProfile
                String activeProfile = intent.getStringExtra(DataWedgeInterface.EXTRA_RESULT_GET_ACTIVE_PROFILE);
                EventBus.getDefault().post(new DataWedgeInterface.MessageEvent(activeProfile));
            }
        }
    }
}
