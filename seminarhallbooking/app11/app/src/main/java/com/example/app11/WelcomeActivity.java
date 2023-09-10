package com.example.app11;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button signUpButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        backButton = findViewById(R.id.backButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Here, you can perform user registration logic.
                // For demonstration purposes, this example allows any non-empty username and password.

                // Successful registration, navigate to SeminarHallAvailabilityActivity
                Intent intent = new Intent(WelcomeActivity.this, SeminarHallAvailabilityActivity.class);
                startActivity(intent);
                finish();


            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to LoginActivity
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}




