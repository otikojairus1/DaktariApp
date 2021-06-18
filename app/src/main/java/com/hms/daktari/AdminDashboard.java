package com.hms.daktari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
    }

    public void callAddDoctorsScreen(View view){
        Intent intent = new Intent(getApplicationContext(), AddDoctors.class);
        intent.putExtra("type", "admin");
        startActivity(intent);
    }

    public void appointmentlist(View view) {
        Intent intent = new Intent(getApplicationContext(), AppointmentList.class);
        //intent.putExtra("username", user.getEmail());
        startActivity(intent);
    }

    public void Departments(View view) {
        Intent intent = new Intent(getApplicationContext(), DoctorDepartmentDashboard.class);
        //intent.putExtra("username", user.getEmail());
        startActivity(intent);
    }
}