package com.example.wasuradananjith.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button nearDoc,vidCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        nearDoc = (Button)findViewById(R.id.btnNearDoc);
        vidCall = (Button)findViewById(R.id.btnVidCall);

        nearDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NearDoctorActivity.class));
                overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom);
                finish();
            }
        });

        vidCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, VideoActivity.class));
                overridePendingTransition(R.anim.slide_out_top,R.anim.slide_in_bottom);
                finish();
            }
        });
    }

}
