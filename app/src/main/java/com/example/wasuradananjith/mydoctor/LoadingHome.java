package com.example.wasuradananjith.mydoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                startActivity(new Intent(LoadingHome.this,HomeScreenActivity.class));
                finish();
            }

        }, 3000);
    }
}
