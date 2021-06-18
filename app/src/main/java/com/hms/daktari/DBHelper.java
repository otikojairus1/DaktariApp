package com.hms.daktari;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static String name = "database";
    static int version = 1;
    //create table if it does not exist
    String createTableUser = "CREATE TABLE if not exists 'user' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' TEXT,"+
            "'password' TEXT, 'email' TEXT, 'gender' TEXT, 'phone' TEXT)";
    String createTableDoctor = "CREATE TABLE if not exists 'doctor' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'username' TEXT,"+
            "'password' TEXT, 'email' TEXT, 'gender' TEXT,'department' TEXT ,'phone' TEXT)";
    String createTableSchedule = "CREATE TABLE if not exists 'schedule' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'doctor' TEXT, 'title' TEXT,"+
            "'date' TEXT, 'time' TEXT)";
    String createTableAppointment = "CREATE TABLE if not exists 'appointment' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'doctor' TEXT, 'patient' TEXT,"+
            "'date' TEXT, 'time' TEXT)";

    public DBHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
        getWritableDatabase().execSQL(createTableDoctor);
        getWritableDatabase().execSQL(createTableSchedule);
        getWritableDatabase().execSQL(createTableAppointment);
    }







    //validating the login

    public boolean isLoginValid(String username, String password){
        String sql = "Select count(*) from user WHERE email='"+username+"' and password='"+password+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        //Log.d("test",statement);
        statement.close();
        return l == 1;
    }
//checking if the doctors email exists
    public boolean isDoctorAccountExists(String email){
        String sql = "Select count(*) from doctor WHERE email='"+email+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        //Log.d("test",statement);
        statement.close();
        return l == 1;
    }

    //check if schedule exists

    public boolean isScheduleExists(String date, String time){
        String sql = "Select count(*) from schedule WHERE time='"+time+"' and date='"+date+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        //Log.d("test",statement);
        statement.close();
        return l == 1;
    }


    //check if the patients account exists
    public boolean isAccountExists(String email){
        String sql = "Select count(*) from user WHERE email='"+email+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        //Log.d("test",statement);
        statement.close();
        return l == 1;
    }

    public boolean isDoctorLoginValid(String username, String password){
        String sql = "Select count(*) from doctor WHERE email='"+username+"' and password='"+password+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        //Log.d("test",statement);
        statement.close();
        return l == 1;
    }
















    //inserts the patieents to the system
    public void insertUser(ContentValues contentValues){
        getWritableDatabase().insert("user","",contentValues);
    }
    public void insertAppointment(ContentValues contentValues){
        getWritableDatabase().insert("appointment","",contentValues);
    }

    public boolean deleteSchedule(String id)
    {
        return getWritableDatabase().delete("schedule", "id" + "=" + id, null) > 0;
    }
    public boolean deleteDoctor(String email)
    {
        return getWritableDatabase().delete("doctor", "email" + "=?", new String[]{email}) > 0;
    }

    public void insertSchedule(ContentValues contentValues){
        getWritableDatabase().insert("schedule","",contentValues);
    }
//inserts the doctors in the system
    public void insertDoctor(ContentValues contentValues){
        getWritableDatabase().insert("doctor","",contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}