package com.example.maxiaowei.webdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private String Url = "http://10.135.100.57:13091/cas-auth/oauth/token?";
    private EditText edUsername;
    private EditText edPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        btLogin = findViewById(R.id.bt_login);
        edUsername.setText("guoyufu");
        edPassword.setText("hq123456");

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usename = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                goLogin(usename, password);
            }
        });
    }

    private void goLogin(String username, String password) {
        AndroidNetworking.post(Url)
                .addBodyParameter("client_id", "EHOME")
                .addBodyParameter("client_secret", "22b6c769c40a49ad9d29d83bbfaa127b")
                .addBodyParameter("grant_type", "password")
                .addBodyParameter("scope", "read")
                .addBodyParameter("add", "user,departments,apps")
                .addBodyParameter("username", username)
                .addBodyParameter("password", password)
                .addHeaders("Content-Type", "application/x-www-form-urlencoded")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("LoginActivity", "response---" + response.toString());
                        String ret = null;
                        try {
                            ret = response.getString("ret");
                            if ("00000".equals(ret)) {
                                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                String access_token = response.getString("access_token");
                                if (access_token != null) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("token", access_token);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "解析异常", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("LoginActivity", "anError---" + anError.toString());
                    }
                });
    }
}
