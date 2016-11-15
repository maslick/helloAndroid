package com.example.maslick.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void clickMe(View view) {
        Intent i = new Intent(this, HahaActivity.class);
        startActivity(i);
    }

}