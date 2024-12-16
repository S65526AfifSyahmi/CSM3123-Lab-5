package com.example.dialapp;

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
        Button btnDial = findViewById(R.id.btn_dial);

        btnDial.setOnClickListener(
            v -> {
                String phoneNumber = etPhoneNumber.getText().toString();
                if (!phoneNumber.isEmpty()) {
                    // Create an Intent to open the dialer
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel: " + phoneNumber));
                    startActivity(dialIntent);
                }
                else {
                    Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        );
    }
}