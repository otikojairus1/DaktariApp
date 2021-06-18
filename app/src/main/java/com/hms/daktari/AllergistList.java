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

public class AllergistList extends AppCompatActivity implements ItemClickListener  {

    AllergistListAdapter adapter;
    String Globalname;
    String GlobalEmail;
    String GlobalDept;
    String Globalmobile, guid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getIntent().getStringExtra("type");
        guid = type;
        setContentView(R.layout.activity_allergist_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

        //String[] ScheduleList = {};
        ArrayList<String> AllergistList = new ArrayList<String>();
        // ScheduleList.add("loading doctors...");
        //start

        String selectQuery = "SELECT  * FROM doctor where department=?";
        DBHelper DB = new DBHelper(getApplicationContext());
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"Allergist"});
        // Move to first row
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            AllergistList.add(cursor.getString(3));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        // return DoctorsList;


        //end
        RecyclerView recyclerView = findViewById(R.id.adjakiduagd8ywt8e);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AllergistListAdapter(this, AllergistList);

        adapter.setClickListener(this::onItemClick);

        recyclerView.setAdapter(adapter);


    }

    private void getlists() {


    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "the daktari patient management system ", Toast.LENGTH_SHORT).show();

    }
}