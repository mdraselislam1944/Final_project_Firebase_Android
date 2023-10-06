package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class HomeActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        progressBar=findViewById(R.id.ProgressBarId);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();
    }
    public  void  doWork(){
        for(progress=20;progress<=100;progress=progress+20){
            try {
                Thread.sleep(500);
                progressBar.setProgress(progress);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void startApp(){
        Intent intent=new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}