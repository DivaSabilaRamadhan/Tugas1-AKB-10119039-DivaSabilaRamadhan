package com.example.divasabilaramadhan_10119039_if1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//Nama : Diva Sabila Ramadhan
//NIM  : 10119039
//Kelas: IF-1
//Tanggal : 22/04/2022

public class Activity_Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Activity_Login.class));
                finish();
            }
        },4000);
    }
}