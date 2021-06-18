package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewAppointment extends AppCompatActivity {
    TextView time;
    TextView desc;
    TextView payment23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
        time = (TextView) findViewById(R.id.aptime);
        desc = (TextView) findViewById(R.id.pemail);
        Intent intent = getIntent();
        payment23 = (TextView) findViewById(R.id.payemntssum1w);
        Integer doctoremail = intent.getIntExtra("doctorEmail",1);

        DBHelper DB = new DBHelper(getApplicationContext());
        SQLiteDatabase db = DB.getReadableDatabase();

        Cursor cursor = db.query("appointment", new String[] { "id",
                        "doctor", "patient","date","time" }, "id" + "=?",
                new String[]{String.valueOf(doctoremail)}, null, null, null,"1");
        if (cursor != null) {
            cursor.moveToFirst();
        }

       // Toast.makeText(this, "You clicked " + cursor.getString(2) , Toast.LENGTH_SHORT).show();

time.setText("On "+cursor.getString(3)+" at "+cursor.getString(4));
desc.setText(cursor.getString(2));











    }
}