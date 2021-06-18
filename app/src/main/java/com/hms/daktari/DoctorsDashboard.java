package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorsDashboard extends AppCompatActivity {
    String GlobalEmail;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_dashboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

    }
    //String email = getIntent().getStringExtra("username");
    public void schedule(View view) {
        String email = getIntent().getStringExtra("useremail");
        Intent intent = new Intent(getApplicationContext(), AddDoctorSchedule.class);

        intent.putExtra("useremail", email);
        GlobalEmail = email;
        startActivity(intent);
    }

    public void viewAppointment(View view) {
       // fetchData();
        String email = getIntent().getStringExtra("useremail");
        Intent intent = new Intent(getApplicationContext(), DoctorAppointmentList.class);
        intent.putExtra("doctoremail", email);
        startActivity(intent);

    }


    public void viewDepartmentDashboard(View view) {
        Intent intent = new Intent(getApplicationContext(), DoctorDepartmentDashboard.class);
        startActivity(intent);
    }
}