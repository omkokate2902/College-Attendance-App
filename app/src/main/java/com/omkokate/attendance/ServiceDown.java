package com.omkokate.attendance;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ServiceDown extends AppCompatActivity {

    TextView issue_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_service_down);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources ().getColor(R.color.purple_700));

        String error_msg = getIntent().getStringExtra("error_msg");

        issue_tv=findViewById(R.id.issue_tv);

        issue_tv.setText(error_msg);



    }
}