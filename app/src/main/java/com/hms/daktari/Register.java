package com.hms.daktari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Register extends AppCompatActivity {
//    EditText email;
//    EditText name;
//    EditText phone;
//    EditText password


    DBHelper DB;



    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        changeStatusBarColor();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//           // reload();
//        }
//    }
//




    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }



    public void firebaseRegister(View view){
//validate inputs
        EditText email = (EditText)findViewById(R.id.editTextEmail);
        EditText name = (EditText)findViewById(R.id.editTextName);
        EditText phone = (EditText)findViewById(R.id.editTextMobile);
        EditText password = (EditText)findViewById(R.id.editTextPassword);
        String useremail = email.getText().toString();
        String userphone = phone.getText().toString();
        String username = name.getText().toString();
        String userpassword = password.getText().toString();
        ProgressBar loading =  (ProgressBar) findViewById(R.id.progressBar2);

        if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(getApplicationContext(), "please enter your email before you proceed",
                    Toast.LENGTH_SHORT).show(); // or break, continue, throw
        }else if(TextUtils.isEmpty(userphone)){
            Toast.makeText(getApplicationContext(), "please enter your phone number starting with 07******** before you proceed",
                    Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "please enter your username before you proceed",
                    Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(userpassword)){
            Toast.makeText(getApplicationContext(), "please enter your password before you proceed",
                    Toast.LENGTH_SHORT).show();
        }else {

            //check existing user

            DBHelper DB = new DBHelper(getApplicationContext());

            if(DB.isAccountExists(useremail)){
                Toast.makeText(getApplicationContext(), "The specified email already exists in the system", Toast.LENGTH_SHORT).show();
            }else{

                ContentValues contentValues = new ContentValues();
                //contentValues.put("id",);
                contentValues.put("username",username);
                contentValues.put("password",userpassword);
                contentValues.put("email",useremail);
                contentValues.put("gender","male");
                contentValues.put("phone",userphone);
                //  DBHelper DB = new DBHelper(getApplicationContext());
                DB.insertUser(contentValues);
                Toast.makeText(getApplicationContext(), "User registered", Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_LONG).show();
            }

    }
    }



}