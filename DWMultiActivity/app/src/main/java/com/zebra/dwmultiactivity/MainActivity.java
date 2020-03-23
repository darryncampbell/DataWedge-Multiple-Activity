package com.zebra.dwmultiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver = new MyReceiver();
    IntentFilter filter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSwitchActivity = findViewById(R.id.btnGoToSecondActivity);
        btnSwitchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivity = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(switchActivity);
            }
        });

        filter.addAction(DataWedgeInterface.ACTION_RESULT_DATAWEDGE_FROM_6_2);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        registerReceiver(myReceiver, filter);

        DataWedgeInterface.sendDataWedgeIntentWithExtra(getApplicationContext(),
                DataWedgeInterface.ACTION_DATAWEDGE_FROM_6_2, DataWedgeInterface. EXTRA_GET_ACTIVE_PROFILE,
                DataWedgeInterface.EXTRA_EMPTY);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(myReceiver);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DataWedgeInterface.MessageEvent event) {
        TextView txtActivieProfile = findViewById(R.id.txtActiveProfile);
        txtActivieProfile.setText(event.activeProfile);
    };
}
