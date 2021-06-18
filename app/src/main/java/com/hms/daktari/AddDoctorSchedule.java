package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentProviderClient;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddDoctorSchedule extends AppCompatActivity {
    DatePicker picker;
    Button btnGet;
    TextView tvw;
    TextView tvw2;
    TimePicker timepicker;
    String GlobalappointmentDate;
    String GlobalappointmentTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_schedule);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
        tvw=(TextView)findViewById(R.id.textView1);
        tvw2=(TextView)findViewById(R.id.textView2);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        timepicker=(TimePicker)findViewById(R.id.timePicker1);
    btnGet = (Button) findViewById(R.id.rrwrwesaxjh);


    btnGet.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvw.setText("Selected appointment Date: "+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear());
            GlobalappointmentDate =  picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear();


            int hour, minute;
            String am_pm;
            if (Build.VERSION.SDK_INT >= 23 ){
                hour = timepicker.getHour();
                minute = timepicker.getMinute();
            }
            else{
                hour = timepicker.getCurrentHour();
                minute = timepicker.getCurrentMinute();
            }
            if(hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            }
            else
            {
                am_pm="AM";
            }
            tvw2.setText("Selected appointment time: "+ hour +":"+ minute+" "+am_pm);
            GlobalappointmentTime = hour +":"+ minute+" "+am_pm;
        }
    });






    }


    public void addschedule(View view) {
        String email = getIntent().getStringExtra("useremail");
        EditText title = (EditText)findViewById(R.id.titleschedule);
        //EditText description = (EditText)findViewById(R.id.descriptionid1);
        //EditText datetime = (EditText)findViewById(R.id.datetime);
        //EditText status = (EditText)findViewById(R.id.descriptionid1);


        String titlestring = title.getText().toString();
      //  String descriptiontext = description.getText().toString();
      //  String datetimetxt = datetime.getText().toString();
        String status = "Active";

        if (TextUtils.isEmpty(titlestring)) {
            Toast.makeText(getApplicationContext(), "please enter appointment title",
                    Toast.LENGTH_SHORT).show(); // or break, continue, throw
        }else{

            DBHelper DB = new DBHelper(getApplicationContext());

            if(DB.isScheduleExists(GlobalappointmentDate,GlobalappointmentTime)){
                Toast.makeText(getApplicationContext(), "The specified schedule already exists in the system", Toast.LENGTH_SHORT).show();
            }else{

                ContentValues contentValues = new ContentValues();
                //contentValues.put("id",);
                contentValues.put("title",titlestring);
                contentValues.put("date",GlobalappointmentDate);
                contentValues.put("time",GlobalappointmentTime);
                contentValues.put("doctor",email);

                //  DBHelper DB = new DBHelper(getApplicationContext());
                DB.insertSchedule(contentValues);
                Toast.makeText(getApplicationContext(), "Scheduled an appointment successfully", Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_LONG).show();
            }

        }
    }



}