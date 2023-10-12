package com.example.custom_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void broadcastIntent(View v){
        Intent i = new Intent(this, Receiver.class);
        i.setAction("com.example.INTENT_1");
        sendBroadcast(i);
    }
}