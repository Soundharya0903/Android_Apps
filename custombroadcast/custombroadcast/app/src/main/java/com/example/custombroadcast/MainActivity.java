package com.example.custombroadcast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void Send(View view){
        // Create a custom intent
        Intent intent = new Intent("com.example.custombroadcast.ACTION");
        intent.putExtra("message","Hello from Custom Broadcast!");
        // Send the intent
        sendBroadcast(intent);


    }
}
