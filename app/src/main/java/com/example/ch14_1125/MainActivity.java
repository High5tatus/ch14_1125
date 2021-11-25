package com.example.ch14_1125;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Intent intent;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Service Test Example");

        intent = new Intent(this, MusicService.class);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                Toast.makeText(getApplicationContext(),"Music Start", Toast.LENGTH_SHORT).show();
                android.util.Log.i("Service Test","startService()");
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                Toast.makeText(getApplicationContext(),"Music Stop", Toast.LENGTH_SHORT).show();
                android.util.Log.i("Service Test","stopService()");
            }
        });

    }
}