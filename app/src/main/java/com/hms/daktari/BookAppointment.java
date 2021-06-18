package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class BookAppointment extends AppCompatActivity {
 TextView doctorAppointment;
    TextView doctorSummary;
    TextView PatientsSummary;
    TextView PaymentSummary;
    String Daktari;
    String s, gtime, gdate, email, gid;
    AlertDialog.Builder builder;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
        builder = new AlertDialog.Builder(this);
        builder.setMessage("You are about to book for an appointment, continue ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Toast.makeText(getApplicationContext(),"Appointment booked successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                })
              ;


        //end

        doctorAppointment = (TextView) findViewById(R.id.dkappointment);
        doctorSummary = (TextView) findViewById(R.id.doctorssum);
        PatientsSummary = (TextView) findViewById(R.id.patientsssum);
        PaymentSummary = (TextView) findViewById(R.id.payemntssum);
        Intent intent = getIntent();
        String doctorname = intent.getStringExtra("doctorName");
        String id = intent.getStringExtra("id");
         email = intent.getStringExtra("uemail");

        doctorAppointment.setText("Book an \n appointment \nwith Dr.\n "+doctorname);

        gid = id;


        DBHelper DB = new DBHelper(getApplicationContext());
        SQLiteDatabase db = DB.getReadableDatabase();

        Cursor cursor = db.query("schedule", new String[] { "id",
                        "doctor", "title","date","time" }, "id" + "=?",
                new String[]{id}, null, null, null,"1");
        if (cursor != null) {
            cursor.moveToFirst();

            String title = cursor.getString(2);
            String doctorEmail = cursor.getString(1);
            String date = cursor.getString(3);
           String time = cursor.getString(4);
            Daktari = doctorEmail;

           gtime = time;
           gdate = date;

           doctorSummary.setText(time);
           PatientsSummary.setText(title);
           PaymentSummary.setText(date);
            doctorAppointment.setText("Book an \n appointment \nwith Dr.\n "+doctorEmail);

             Toast.makeText(getApplicationContext(),cursor.getString(0) ,Toast.LENGTH_SHORT).show();
//            emailfield.setText(doctorEmail);
//            phonefield.setText(phone);
//            usernamefield.setText(dname);
//            department.setText(departmentn);
//            names.setText(dname);

        }





        //PatientsSummary.setText( s );



    }

    public void BookAppointment(View view) {

        ContentValues contentValues = new ContentValues();
        //contentValues.put("id",);
        contentValues.put("doctor",Daktari);
        contentValues.put("patient",email);
        contentValues.put("date",gdate);
        contentValues.put("time",gtime);

          DBHelper DB = new DBHelper(getApplicationContext());
        DB.insertAppointment(contentValues);
        //deletes schedule
        DBHelper DB2 = new DBHelper(getApplicationContext());
         DB2.deleteSchedule(gid);
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Appointment Booking confirmation");
        alert.show();



//
//        Toast.makeText(getApplicationContext(), "You have booked an appointment successfully",
//                Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
//        startActivity(intent);
//        finish();

    }

    private void AddAppointment(String patientsName, String DoctorsEmail, String AppointmentTime) {

    }
}