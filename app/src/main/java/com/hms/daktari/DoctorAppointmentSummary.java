package com.hms.daktari;

import androidx.annotation.NonNull;
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

public class DoctorAppointmentSummary extends AppCompatActivity {
    TextView appointmentTime;
    TextView PatientsEmail;
    TextView PaymentMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment_summary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
        String email1 = getIntent().getStringExtra("email");
        appointmentTime = (TextView) findViewById(R.id.aptime232);
        PatientsEmail = (TextView) findViewById(R.id.pemai4323rl);
        PaymentMode = (TextView) findViewById(R.id.payemntss3452um1w);


        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Appointments").document(email1);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        String firebasetime = (String) document.getData().get("appointmentTime");
                        String firebasepatientsname = (String) document.getData().get("patientname");
                        String fpayment = (String) document.getData().get("payment mode");

                        appointmentTime.setText(firebasetime);
                        PatientsEmail.setText(firebasepatientsname);
                        PaymentMode.setText(fpayment);
//                        Intent intent = new Intent(getApplicationContext(), DoctorAppointmentSummary.class);
//                        intent.putExtra("time", firebasetime );
//                        intent.putExtra("patientsname", firebasepatientsname );
//                        intent.putExtra("time", firebasetime );
//                        intent.putExtra("mode", fpayment );
//                        startActivity(intent);


                    } else {
                        // Log.d(TAG, "No such document");
                        Toast.makeText(getApplicationContext(), "You have no appointments! create a schedule for patients to book for an appointment with you",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    //Log.d(TAG, "get failed with ", task.getException());
                    Toast.makeText(getApplicationContext(), "we have a problem authorizing you, please try again later or consult the admin : .",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cancelappointment(View view) {
        Toast.makeText(getApplicationContext(), "Appointment cancelled.",
                Toast.LENGTH_LONG).show();
    }
}