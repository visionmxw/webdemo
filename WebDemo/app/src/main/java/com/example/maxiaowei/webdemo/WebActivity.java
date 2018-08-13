package com.example.maxiaowei.webdemo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;
    private String token;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webview);

        token = getIntent().getStringExtra("token");
        url = getIntent().getStringExtra("url");

        if (token != null && url != null) {
            initWebView(url);
        }
    }

    private void initWebView(String url) {

        webView.setWebViewClient(new WebViewClient() {
            @Override //返回false则使用当前的WebView加载链接
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override //页面打开时执行操作,显示一个进度条
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override //页面关闭时执行操作
            public void onPageFinished(WebView view, String url) {

            }
        });

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); //支持js
        webSettings.setUseWideViewPort(true); //自适应屏幕 可以任意比例缩放
        webSettings.setLoadWithOverviewMode(true);//设置网页是否支持概览模式
        webSettings.setBuiltInZoomControls(true); //设置缩放按钮
        webSettings.setSupportZoom(true); //使页面支持缩放
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //不支持缓存

        Map<String, String> headers = new HashMap<>();
        headers.put("access_token", token);
        webView.loadUrl(url, headers);
    }
}
