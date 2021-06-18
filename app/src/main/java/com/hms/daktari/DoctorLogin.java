package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
    }

    public void loginDoctor(View view) {
    //pick user inputs
        EditText email = (EditText)findViewById(R.id.doctoremaillogin);
        EditText password = (EditText)findViewById(R.id.doctorpasswordlogin);
        String useremail = email.getText().toString();
        String userpwd = password.getText().toString();
        if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(getApplicationContext(), "please enter your email before you proceed",
                    Toast.LENGTH_SHORT).show(); // or break, continue, throw
        }else if(TextUtils.isEmpty(userpwd)){
            Toast.makeText(getApplicationContext(), "please enter your password before you proceed",
                    Toast.LENGTH_SHORT).show();
        }else {

            DBHelper DB = new DBHelper(getApplicationContext());

            if (!DB.isDoctorLoginValid(useremail, userpwd)) {
                //error message if there is no match
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DoctorsDashboard.class);
                 intent.putExtra("useremail", useremail);
                startActivity(intent);
            }

        }
    }
}