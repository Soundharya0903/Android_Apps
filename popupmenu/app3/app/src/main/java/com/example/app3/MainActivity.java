package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView selectedPlanTextView;
    private EditText principalEditText, timeEditText;
    private Button planButton, calculateButton;
    private String selectedPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedPlanTextView = findViewById(R.id.selectedPlanTextView);
        planButton = findViewById(R.id.planButton);
        principalEditText = findViewById(R.id.principalEditText);
        timeEditText = findViewById(R.id.timeEditText);
        calculateButton = findViewById(R.id.calculateButton);

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInterest();
            }
        });
    }

    private void showPopupMenu(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(this, anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            selectedPlan = item.getTitle().toString();
            selectedPlanTextView.setText(selectedPlan);
            return true;
        });

        popupMenu.show();
    }

    private void calculateInterest() {
        double principal = Double.parseDouble(principalEditText.getText().toString());
        int timePeriod = Integer.parseInt(timeEditText.getText().toString());

        double interestRate = 0.0;
        if (selectedPlan.equals("Plan A")) {
            interestRate = 0.05;
        } else if (selectedPlan.equals("Plan B")) {
            interestRate = 0.07;
        } else if (selectedPlan.equals("Plan C")) {
            interestRate = 0.09;
        }

        double interest = principal * interestRate * timePeriod;

        String message = "Interest for " + selectedPlan + ": " + interest;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

