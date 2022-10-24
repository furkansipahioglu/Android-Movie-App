package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
/* iconlar attribute telifli*/
//<a target="_blank" href="https://icons8.com/icon/114429/documentary">Documentary</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>
public class SplashActivity extends AppCompatActivity {


    private final int splashdisplay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, splashdisplay);
    }

    }
