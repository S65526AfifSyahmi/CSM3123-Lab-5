package com.example.smsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etPhoneNumber = findViewById(R.id.et_phoneNumber);
        EditText etMessage = findViewById(R.id.et_message);
        Button btnDial = findViewById(R.id.btn_send);

        btnDial.setOnClickListener(
            v -> {
                String phoneNumber = etPhoneNumber.getText().toString();
                String message = etMessage.getText().toString();
                if (!phoneNumber.isEmpty() && !message.isEmpty()) {
                    // Use ACTION_SENDTO to send SMS
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    // Set the SMS URI
                    smsIntent.setData(Uri.parse("smsto: " + phoneNumber));
                    // Set the SMS message body
                    smsIntent.putExtra("sms_body", message);
                    startActivity(smsIntent);
                }
                else {
                    Toast.makeText(this, "Please enter a phone number and message", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }
}