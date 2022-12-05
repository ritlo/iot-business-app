package com.lohani.fireproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity {

    TextView counter;
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("Firetest");
        counter = findViewById(R.id.countInt);
        Log.d("App opened", "TRUE");
        sharedPreferences = getSharedPreferences("VALUESTORE",MODE_PRIVATE);
        String pc = sharedPreferences.getString("personCount","0");
        Log.d("Person Count", "onCreate: "+pc);
        counter.setText(pc);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                String s = sharedPreferences.getString(key,"0");
                counter.setText(s);
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

        }


    }






