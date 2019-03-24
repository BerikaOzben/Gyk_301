package com.example.gyk_301;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class VoiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button recordVoiceButton;
    Button stopVoiceButton;
    Button playVoiceButton;

    private MediaRecorder recorder; //kayıt yaparken
    private MediaPlayer player;
    private final String filepath = Environment.getExternalStorageDirectory().getPath() + "/record.3gp"; //kaydı kaydedecegimiz dosya kodu
    private static final int REQUEST_AUDIO_PERMISSION_CODE = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        recordVoiceButton=(Button)findViewById(R.id.record_voice_button);
        stopVoiceButton=(Button)findViewById(R.id.stop_voice_button);
        playVoiceButton=(Button)findViewById(R.id.play_voice_button);


        recordVoiceButton.setOnClickListener(this); //butonları tanımlamak için
        stopVoiceButton.setOnClickListener(this);
        playVoiceButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        if (view == recordVoiceButton) {
            if(checkPermissions()){
                startRecording();
            } else {
                requestPermissions();
                startRecording();
            }
        } else if (view == stopVoiceButton) {
            stopRecording();
        } else if (view == playVoiceButton) {
            startPlaying();
        }

    }




    private void startRecording() {

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //sesin dilini çözen
        recorder.setOutputFile(filepath);

        try {
            recorder.prepare();
            recorder.start();
            Toast.makeText(this,"Kayıt yapılıyor", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void stopRecording() {
        if (recorder != null) {
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
            Toast.makeText(getApplicationContext(),"Kayıt Durduruldu",Toast.LENGTH_LONG).show();}
    }

    private void startPlaying() {
        player = new MediaPlayer();
        player.setVolume(1.0f, 1.0f);
        try {
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            Toast.makeText(getApplicationContext(),"Kayıt Çalıyor",Toast.LENGTH_LONG).show();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer arg0) {
                    player.stop();
                    player.release();
                    player = null;
                }
            });
        } catch (Exception e) {
        }
    }


    public boolean checkPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(VoiceActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }



}