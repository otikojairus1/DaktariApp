package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//public class Login extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//    }
//}


public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        changeStatusBarColor();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View View){
        startActivity(new Intent(getApplicationContext(),Register.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }




    public void firebaseLogin(View view){
        EditText email = (EditText)findViewById(R.id.editTextEmail);
        EditText password = (EditText)findViewById(R.id.editTextPassword);
        String useremail = email.getText().toString();
        String userpwd = password.getText().toString();

        if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(getApplicationContext(), "please enter your email before you proceed",
                    Toast.LENGTH_SHORT).show(); // or break, continue, throw
        }else if(TextUtils.isEmpty(userpwd)){
            Toast.makeText(getApplicationContext(), "please enter your password before you proceed",
                    Toast.LENGTH_SHORT).show();
        }else{
            DBHelper DB = new DBHelper(getApplicationContext());
            //calling the isloginvalid method in databasehelper.java that checks if a match exists in the database with the submitted one
            if (!DB.isLoginValid(useremail, userpwd)) {
                //error message if there is no match
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            } else {
//                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(intent);
                if(useremail.equals("admin@daktari.com")){
                    Toast.makeText(getApplicationContext(), "admin logged in Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                   // intent.putExtra("username", firebaseusername);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                    intent.putExtra("uemail", useremail);
                    startActivity(intent);
                }



            }

        }



    }

    public void doctorloginscreen(View view) {
        Intent intent = new Intent(getApplicationContext(), DoctorLogin.class);

        startActivity(intent);
    }
}