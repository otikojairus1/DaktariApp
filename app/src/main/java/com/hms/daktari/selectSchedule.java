package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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

import  com.hms.daktari.SchedulesAdapter.*;

public class selectSchedule extends AppCompatActivity implements ItemClickListener  {

    SchedulesAdapter adapter;
    String global,globalemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_schedule);
        String email = getIntent().getStringExtra("uemail");
        globalemail = email;

        //String[] ScheduleList = {};
        ArrayList<String> ScheduleList = new ArrayList<String>();
       // ScheduleList.add("loading doctors...");
       //start


        String selectQuery = "SELECT  * FROM schedule";
        DBHelper DB = new DBHelper(getApplicationContext());
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        { String id = cursor.getString(1);
            ScheduleList.add(cursor.getString(1));
            global = cursor.getString(0);
            cursor.moveToNext();

        }
        cursor.close();
        db.close();


        //end
        RecyclerView recyclerView = findViewById(R.id.schedulelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SchedulesAdapter(this, ScheduleList);

        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);


    }

    private void getlists() {


    }


    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        String scheduleSeleccted = adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), BookAppointment.class);
        intent.putExtra("doctorName", scheduleSeleccted);
        intent.putExtra("id", global);

        intent.putExtra("uemail", globalemail);
        startActivity(intent);
    }
}