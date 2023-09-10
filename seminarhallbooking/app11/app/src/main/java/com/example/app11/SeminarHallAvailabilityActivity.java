package com.example.app11;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SeminarHallAvailabilityActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView availableHallsTextView;
    private Button backButton, bookButton;

    // Sample data source for available halls on specific dates
    private ArrayList<HallAvailability> hallAvailabilityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_hall_availability);

        datePicker = findViewById(R.id.datePicker);
        availableHallsTextView = findViewById(R.id.availableHallsTextView);
        backButton = findViewById(R.id.backButton);
        bookButton = findViewById(R.id.bookButton);

        // Initialize the data source with sample hall availability data
        initializeHallAvailabilityData();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to LoginActivity
                Intent intent = new Intent(SeminarHallAvailabilityActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement your booking logic here
                Date selectedDate = getDateFromDatePicker(datePicker);
                boolean bookingSuccess = bookHall(selectedDate);

                // Replace "5551234567" with the recipient's phone number
                String phoneNumber = "9943009188";
                String message = "Your hall booking is confirmed for the selected date.";

                // Create the SMS intent
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:" + phoneNumber));
                smsIntent.putExtra("sms_body", message);

                // Start the SMS intent
                startActivity(smsIntent);



                // Show a success message or perform additional actions
                // For example, you can display a Toast message

                Toast.makeText(SeminarHallAvailabilityActivity.this, "Hall booked successfully!", Toast.LENGTH_SHORT).show();

                // Show a failure message or perform additional actions
                // For example, you can display a Toast message

            }


            private boolean bookHall(Date selectedDate) {
                for (HallAvailability hallAvailability : hallAvailabilityList) {
                    if (hallAvailability.getDate().equals(selectedDate)) {
                        // Check if the hall is already booked
                        if (hallAvailability.isBooked()) {
                            return false; // Hall is already booked
                        } else {
                            hallAvailability.setBooked(true); // Mark the hall as booked
                            return true; // Booking successful
                        }
                    }
                }
                return false; // Hall availability not found for the selected date

            }
        });

        // Set a listener to update available seminar halls when date changes
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }

            private void showDatePickerDialog() {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        SeminarHallAvailabilityActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Update available halls based on selected date
                                updateAvailableHalls(year, month, dayOfMonth);
                            }
                        },
                        year, month, dayOfMonth
                );

                datePickerDialog.show();
            }
        });
    }

    private void initializeHallAvailabilityData() {
        // Initialize the list with sample data
        hallAvailabilityList = new ArrayList<>();

        // Add sample hall availability entries (you should replace this with your actual data)
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.AUGUST, 15);

        hallAvailabilityList.add(new HallAvailability(calendar.getTime(), "Hall A"));

        // Example 2: Hall B is available on August 16, 2023
        calendar.set(2023, Calendar.AUGUST, 16);
        hallAvailabilityList.add(new HallAvailability(calendar.getTime(), "Hall B"));

        // Add more sample data as needed
    }

    private void updateAvailableHalls(int year, int month, int dayOfMonth) {
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(year, month, dayOfMonth);
        Date selectedDate = selectedCalendar.getTime();
        String availableHalls = getAvailableHallsForDate(selectedDate);
        availableHallsTextView.setText(availableHalls);
    }



    private Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        return new Date(year - 1900, month, day); // Adjust year offset
    }

    private String getAvailableHallsForDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String formattedDate = dateFormat.format(date);

        for (HallAvailability hallAvailability : hallAvailabilityList) {
            if (hallAvailability.getDate().equals(formattedDate)) {
                if (hallAvailability.isBooked()) {
                    return "Halls for " + formattedDate + " are already booked.";
                } else {
                    return "Available halls for " + formattedDate + ":\n" + hallAvailability.getAvailableHalls();
                }
            }
        }

        return "No available halls for " + formattedDate;
    }

    // Sample class to represent hall availability data


    public class HallAvailability {
        private Date date;
        private String availableHalls;
        private boolean booked; // New field to track booking status

        public HallAvailability(Date date, String availableHalls) {
            this.date = date;
            this.availableHalls = availableHalls;
            this.booked = false; // Initialize as not booked
        }

        public Date getDate() {
            return date;
        }

        public String getAvailableHalls() {
            return availableHalls;
        }

        public boolean isBooked() {
            return booked;
        }

        public void setBooked(boolean booked) {
            this.booked = booked;
        }


    }

}




