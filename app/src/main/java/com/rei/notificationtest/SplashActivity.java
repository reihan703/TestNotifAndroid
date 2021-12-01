package com.rei.notificationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Sampler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;
    private int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.persentase);
        progressBar.setProgress(0);

        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String val = value + "%";
                textView.setText(val);
                if (value == progressBar.getMax()) {
                    Toast.makeText(getApplicationContext(), "Progress Completed",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                value++;
            }
        };

        // Thread untuk updating progress pada ProgressBar di Latar Belakang
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0; i<=progressBar.getMax(); i++){
                        progressBar.setProgress(i);
                        // Mengirim pesan dari handler, untuk diproses didalam thread
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(100);
                    }
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        });
        thread.start();
    }
}