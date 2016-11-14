package com.example.maslick.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lombok.Data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Data
    class Helloworld {
        String abd;
    }

}