package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorProfile extends AppCompatActivity {
    TextView emailfield;
    TextView phonefield;
    TextView usernamefield;
    TextView department;
    TextView names, doctor;
    String gemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
            doctor = (TextView)findViewById(R.id.deletedoctors);
            String type = getIntent().getStringExtra("type");
            if (type.equals("admin")){
                doctor.setVisibility(View.VISIBLE);
            }

        }

        emailfield = (TextView)findViewById(R.id.emailfeield);
        phonefield = (TextView)findViewById(R.id.phonefieereld);
        usernamefield = (TextView)findViewById(R.id.usernereramefield);
        names = (TextView)findViewById(R.id.tv_iuguigname);
        department = (TextView)findViewById(R.id.paymenerertid);
        Intent intent = getIntent();
        String name = intent.getStringExtra("doctorName");

        DBHelper DB = new DBHelper(getApplicationContext());
            SQLiteDatabase db = DB.getReadableDatabase();

            Cursor cursor = db.query("doctor", new String[] { "username",
                            "email", "gender","department","phone" }, "email" + "=?",
                    new String[]{name}, null, null, null,"1");
            if (cursor != null) {
                cursor.moveToFirst();

                String gender = cursor.getString(2);
              String doctorEmail = cursor.getString(1);
              gemail = doctorEmail;
                String departmentn = cursor.getString(3);
            String phone = cursor.getString(4);
                String dname = cursor.getString(0);
              //String doctorGender = cursor.getString(3);

               // Toast.makeText(getApplicationContext(),cursor.getString(0) ,Toast.LENGTH_SHORT).show();
                emailfield.setText(doctorEmail);
                phonefield.setText(phone);
                usernamefield.setText(dname);
                department.setText(departmentn);
                names.setText(dname);

            }






    }

    public void deleteDoctordetails(View view) {

        //deletes schedule
        DBHelper DB2 = new DBHelper(getApplicationContext());
        DB2.deleteDoctor(gemail);
        Toast.makeText(getApplicationContext(),"doctor deleted successfully" ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);

        startActivity(intent);
        finish();
    }
}