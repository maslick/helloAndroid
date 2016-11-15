package com.example.maslick.helloworld;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by maslick on 14.11.2016.
 */

public class HahaActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button button;
    private boolean isSpinning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haha);
        button = (Button) findViewById(R.id.toggleBtn);
        button.setText("Hide");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isSpinning) {
                progressBar.setVisibility(View.INVISIBLE);
                button.setText("Show");
                isSpinning = !isSpinning;
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                button.setText("Hide");
                isSpinning = !isSpinning;
            }
        }
    };

    public void toggleBtn(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long futuretime = System.currentTimeMillis() + 10;
                while (System.currentTimeMillis() < futuretime) {
                    synchronized (this) {
                        try {
                            wait(futuretime - System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                handler.sendEmptyMessage(0);
            }
        };

        Thread myThread = new Thread(runnable);
        myThread.start();
    }
}
