package com.example.app11;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace with your authentication logic
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();


                // Successful login, navigate to SeminarHallAvailabilityActivity
                Intent intent = new Intent(MainActivity.this, SeminarHallAvailabilityActivity.class);
                startActivity(intent);
                finish();

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to SignUpActivity
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }}


