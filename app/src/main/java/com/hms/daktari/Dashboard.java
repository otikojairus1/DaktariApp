package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Dashboard extends AppCompatActivity {
String globalemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        String email = getIntent().getStringExtra("uemail");
        globalemail = email;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
        TextView emaildisplay = findViewById(R.id.usernameid);


        String message = "Hello "+globalemail;

        emaildisplay.setText(message);
    }

    public void checkSchedules(View view) {

        Intent intent = new Intent(getApplicationContext(), selectSchedule.class);
        intent.putExtra("uemail", globalemail);
        startActivity(intent);
    }

    public void accountpage(View view) {


    }

    public void doctorlist(View view) {
        Intent intent = new Intent(getApplicationContext(), DoctorsList.class);
        intent.putExtra("type", "patient");
        startActivity(intent);
    }
}