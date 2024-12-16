package com.example.smsmanagerapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SMS_PERMISSION = 1;

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
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS_PERMISSION);
                    }
                    else {
                        sendSMS(phoneNumber, message);
                    }
                }
                else {
                    Toast.makeText(this, "Please enter a phone number and message", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted. Sending SMS", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission denied. Cannot send SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }
}