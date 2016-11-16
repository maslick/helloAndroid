package com.example.maslick.helloworld;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by maslick on 14.11.2016.
 */

public class HahaActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button button;
    private boolean isSpinning = true;

    NotificationCompat.Builder notification;
    private static int uniqueID = 12345;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.haha);
        button = (Button) findViewById(R.id.toggleBtn);
        button.setText("Hide");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // notification
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        notifyy("Heartman", "You opened settings activity", "readme");
    }

    private void notifyy(String title, String text, String ticker) {
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker(ticker);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(title);
        notification.setContentText(text);

        Intent intent = new Intent(this, HahaActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID++, notification.build());
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isSpinning) {
                progressBar.setVisibility(View.INVISIBLE);
                button.setText("Show");
                isSpinning = !isSpinning;
                toast("not spinning...");
                notifyy("Heartman", "not spinning", "not spinning...");
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                button.setText("Hide");
                isSpinning = !isSpinning;
                toast("spinning...");
                notifyy("Heartman", "spinning", "spinning...");
            }
        }
    };

    public void toggleBtn(View view) {
        Runnable runnable = () -> {
            handler.sendEmptyMessage(0);
        };

        Thread myThread = new Thread(runnable);
        myThread.start();
    }

    private void toast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 20);
        toast.show();
    }
}
