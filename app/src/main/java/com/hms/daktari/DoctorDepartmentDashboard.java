package com.hms.daktari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class DoctorDepartmentDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_department_dashboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
    }

    public void viewDepartmentDashboard(View view) {

    }

    public void allergists(View view) {

//        Toast.makeText(getApplicationContext(), "Allergist clicked",
//                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), AllergistList.class);
        //intent.putExtra("username", user.getEmail());
        startActivity(intent);
    }

    public void Dermatology(View view) {

        Intent intent = new Intent(getApplicationContext(), DermatologyList.class);
        //intent.putExtra("username", user.getEmail());
        startActivity(intent);
    }

    public void cardiology(View view) {

        Intent intent = new Intent(getApplicationContext(), Cardiology.class);
        //intent.putExtra("username", user.getEmail());
        startActivity(intent);
    }

    public void Endocriology(View view) {

        Intent intent = new Intent(getApplicationContext(), EndocriologyList.class);
        //intent.putExtra("username", user.getEmail());
        startActivity(intent);
    }
}