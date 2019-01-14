package com.example.markr.gymyardjokes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

public class StartScreen extends AppCompatActivity {

    //SharedPreferences sharedPref;
    //boolean loggedToday = false;
    private Intent intent;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                start();
            }
        }, 4000);
    }

    /** Called when the user taps the Send button */
    public void GotoMainMenu(View view) {
        start();
    }

    private void start(){
        intent = new Intent(StartScreen.this, CalendarActivity.class);
        timer.cancel();
        startActivity(intent);
    }
}
