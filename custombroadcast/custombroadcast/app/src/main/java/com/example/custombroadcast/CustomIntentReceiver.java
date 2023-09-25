package com.example.custombroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Check if the received intent has the custom action
        String message = intent.getStringExtra("message");
        if (message!=null) {
            // Show a toast message
            Toast.makeText(context, "Custom intent received!"+message, Toast.LENGTH_LONG).show();
        }
    }
}
