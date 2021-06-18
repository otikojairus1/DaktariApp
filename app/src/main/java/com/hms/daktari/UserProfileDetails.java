package com.hms.daktari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfileDetails extends AppCompatActivity {
TextView emailfield;
TextView phonefield;
TextView usernamefield;
TextView payment;
    TextView Prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_details);

        emailfield = (TextView)findViewById(R.id.emailfield);
        phonefield = (TextView)findViewById(R.id.phonefield);
        usernamefield = (TextView)findViewById(R.id.usernamefield);
        payment = (TextView)findViewById(R.id.paymentid);
        Prof = (TextView)findViewById(R.id.tv_name);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String username = intent.getStringExtra("username");
        String payment1 = intent.getStringExtra("payment");


        emailfield.setText(email);
        phonefield.setText(phone);
        usernamefield.setText(username);
        payment.setText(payment1);
        Prof.setText(username);


    }
}