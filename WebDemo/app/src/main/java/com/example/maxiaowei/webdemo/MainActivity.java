package com.example.maxiaowei.webdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvToken;
    private Button btITMS;
    private Button btMODS;
    private Button btOA;
    private String token;
    private String BaseUrl = "http://10.125.2.48:8080/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvToken = findViewById(R.id.tv_token);
        btITMS = findViewById(R.id.bt_ITMS);
        btMODS = findViewById(R.id.bt_MODS);
        btOA = findViewById(R.id.bt_OA);

        token = getIntent().getStringExtra("token");
        tvToken.setText(token);

        btITMS.setOnClickListener(this);
        btMODS.setOnClickListener(this);
        btOA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.bt_ITMS:
                intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("url", "http://10.125.2.48:8080/itsm/rbsx_app_sso.jsp");
                startActivity(intent);
                break;
            case R.id.bt_MODS:
                intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("url", "https://10.125.50.3:7001/login");
                startActivity(intent);
                break;
            case R.id.bt_OA:
                intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("url", "http://10.127.135.13:7001/servlet/com.zotn.screens.HomeServlet");
                startActivity(intent);
                break;
        }
    }
}
