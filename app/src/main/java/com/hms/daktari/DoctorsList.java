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

import  com.hms.daktari.DoctorListAdapter.*;

public class DoctorsList extends AppCompatActivity implements ItemClickListener  {

    DoctorListAdapter adapter;
    String Globalname;
    String GlobalEmail;
    String GlobalDept;
    String Globalmobile, guid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getStringExtra("type");
        guid = type;
        setContentView(R.layout.activity_doctors_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

        //String[] ScheduleList = {};
        ArrayList<String> DoctorsList = new ArrayList<String>();
        // ScheduleList.add("loading doctors...");
        //start

        String selectQuery = "SELECT  * FROM doctor";
     DBHelper DB = new DBHelper(getApplicationContext());
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            DoctorsList.add(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
       // return DoctorsList;


        //end
        RecyclerView recyclerView = findViewById(R.id.dkjsigaisasogasia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DoctorListAdapter(this, DoctorsList);

        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);


    }

    private void getlists() {


    }


    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        String scheduleSeleccted = adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), DoctorProfile.class);
        intent.putExtra("doctorName", scheduleSeleccted);
        intent.putExtra("type", guid);

        startActivity(intent);
    }
}