package com.example.gyk_301;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String packageContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void FotografVideoCek(View view) {

        Intent i = new Intent(MainActivity.this, FotografVideoActivity.class);
        startActivity(i);


    }

    public void smsGonder(View view) {
        Intent i = new Intent(MainActivity.this, sms.class);
        startActivity(i);
    }

    public void aramaYap(View view) {

        Intent i = new Intent(MainActivity.this, AramaActivity.class);
        startActivity(i);
    }

    public void interneteGir(View view) {

        Intent i = new Intent(MainActivity.this, internet.class);
        startActivity(i);

    }

    public void harita(View view) {

        Intent i = new Intent(MainActivity.this, harita.class) ;
        startActivity(i);

    }
    public void sesKayit (View view) {

        Intent i=new Intent(this,VoiceActivity.class);
        startActivity(i);
    }

}
