package com.example.gyk_301;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class internet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        WebView vw=(WebView)findViewById(R.id.webView);
        vw.getSettings().getJavaScriptEnabled();
        vw.loadUrl("https://google.com.tr");


    }


}
