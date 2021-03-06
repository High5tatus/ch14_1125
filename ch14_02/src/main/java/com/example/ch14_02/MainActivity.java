package com.example.ch14_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ivBattery;
    EditText edtBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Battery State Check");

        ivBattery = findViewById(R.id.ivBattery);
        edtBattery = findViewById(R.id.edtBattery);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,iFilter);
    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                edtBattery.setText("Now : " + remain + "%\n");

                if (remain >= 90) ivBattery.setImageResource(R.drawable.battery_100);
                else if (remain >= 70) ivBattery.setImageResource(R.drawable.battery_80);
                else if (remain >= 50) ivBattery.setImageResource(R.drawable.battery_60);
                else if (remain >= 10) ivBattery.setImageResource(R.drawable.battery_20);
                else ivBattery.setImageResource(R.drawable.battery_0);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug) {
                    case 0:
                        edtBattery.append("Connected");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edtBattery.append("AC Connecting");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edtBattery.append("USB Connecting");
                        break;
                }
            }
        }
    };


}