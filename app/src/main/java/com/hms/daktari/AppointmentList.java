package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;



public class AppointmentList extends AppCompatActivity implements AppointmentAdapter.ItemClickListener {

    AppointmentAdapter adapter;
    String GlobalPatientName;
    String GlobalDoctorname;
    String GlobalAppointmenttime;
    String GlobalPayment, AppointmentTime, patientsemail,AppointmentDate ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

        //String[] ScheduleList = {};
        ArrayList<String> AppointmentList = new ArrayList<String>();
        // ScheduleList.add("loading doctors...");
        //start

       String selectQuery = "SELECT  * FROM appointment";
     DBHelper DB = new DBHelper(getApplicationContext());
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            AppointmentList.add(cursor.getString(1));
            AppointmentTime = cursor.getString(4);
            patientsemail = cursor.getString(2);
            AppointmentDate = cursor.getString(3);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();


            //end
            RecyclerView recyclerView = findViewById(R.id.appointmentlist);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new AppointmentAdapter(this, AppointmentList);

            adapter.setClickListener(this);

            recyclerView.setAdapter(adapter);


    }

    private void getlists() {


    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        String doctoremail = adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), ViewAppointment.class);
        intent.putExtra("doctorEmail", position);


        startActivity(intent);
    }
}