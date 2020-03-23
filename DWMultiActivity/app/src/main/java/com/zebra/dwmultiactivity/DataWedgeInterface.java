package com.zebra.dwmultiactivity;

import android.content.Context;
import android.content.Intent;

public class DataWedgeInterface {

    public static class MessageEvent {
        String activeProfile;

        public MessageEvent(String activeProfile) {
            this.activeProfile = activeProfile;
        }
    }

    public static final String ACTION_DATAWEDGE_FROM_6_2 = "com.symbol.datawedge.api.ACTION";
    public static final String ACTION_RESULT_DATAWEDGE_FROM_6_2 = "com.symbol.datawedge.api.RESULT_ACTION";
    public static final String EXTRA_RESULT_GET_ACTIVE_PROFILE = "com.symbol.datawedge.api.RESULT_GET_ACTIVE_PROFILE";
    public static final String EXTRA_GET_ACTIVE_PROFILE = "com.symbol.datawedge.api.GET_ACTIVE_PROFILE";
    public static final String EXTRA_EMPTY = "";

    public static void sendDataWedgeIntentWithExtra(Context context, String action, String extraKey, String extraValue)
    {
        Intent dwIntent = new Intent();
        dwIntent.setAction(action);
        dwIntent.putExtra(extraKey, extraValue);
        context.sendBroadcast(dwIntent);
    }

}
