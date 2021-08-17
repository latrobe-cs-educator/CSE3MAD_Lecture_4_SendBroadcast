package com.example.sendbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver rx;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configure receiver
        IntentFilter filter = new IntentFilter();
        //listen for app action
        filter.addAction("com.example.sendbroadcast");
        //receive system power disconnected broadcast
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        rx = new MyReceiver();
        registerReceiver(rx, filter);

        //Set up button
        sendBtn = (Button)findViewById(R.id.button);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.sendbroadcast");
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }
        });

    }

    //Unregister the broadcast receiver when it is no longer needed:
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(rx);
    }
}