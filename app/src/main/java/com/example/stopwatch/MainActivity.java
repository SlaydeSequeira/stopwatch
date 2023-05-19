package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView startImage, stopImage, pauseImage;
    private boolean isRunning;
    private long startTime, elapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startImage = findViewById(R.id.start);
        stopImage = findViewById(R.id.stop);
        pauseImage = findViewById(R.id.pause);

        // Set click listeners for the images
        startImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        stopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        pauseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });
    }

    private void stopTimer() {
        if (isRunning) {
            isRunning = false;
            elapsedTime = 0;
            updateTimer(elapsedTime);
        }
    }
    private void startTimer() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isRunning) {
                        elapsedTime = System.currentTimeMillis() - startTime;
                        updateTimer(elapsedTime);
                        handler.postDelayed(this, 1000);
                    }
                }
            }, 1000); // Add a delay of 1 second before the first execution
        }
    }


    private void pauseTimer() {
        isRunning = false;
    }

    private void updateTimer(long elapsedTime) {
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours = hours % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        TextView timerTextView = findViewById(R.id.timerTextView);
        timerTextView.setText(time);
    }
}
