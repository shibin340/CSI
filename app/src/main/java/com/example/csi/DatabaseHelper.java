package com.example.csi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class DatabaseHelper extends SQLiteOpenHelper {
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageintobytes;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text,email text primary key,Phonenum text,password text,dob text,task text,completed int,image BLOB default null,isAuthorized int,Address text default null,Nationality text default null,UID text default null,PAN text default null,Language text default null,Education text default null,DOJ text default null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //Insertion
    public boolean insert(String username,String email,String password,String dob,String task1,String phno){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("dob",dob);
        contentValues.put("password",password);
        contentValues.put("task",task1);
        contentValues.put("completed",0);
        contentValues.put("isAuthorized",0);
        contentValues.put("Phonenum",phno);
        long ins = db.insert("user",null,contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }
    public boolean insertstatus(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update user set completed =1 where email=?",new String[]{email});
        return true;
    }

    //Add the Task
    public boolean assignTask(String email,String Task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update user set completed = 0, task = ? where email=?",new String[]{Task,email});
        return true;
    }

    //Check if email exists
    public boolean checkmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?", new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    //Check mail and password
    public Boolean chkmailpass(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email= ? and password = ?",new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //check approval
    public Boolean checkapproval(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email = '"+email+"' and isAuthorized = 1",null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //after tick
    public Boolean tickk(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isAuthorized",1);
        String whereClause = "email=?";
        String whereArgs[] = {email};
        long ins;
        ins=db.update("user", contentValues, whereClause, whereArgs);
        if(ins==-1)
        {
            return false;
        }
        else
            return true;

    }

    //after cross
    public Boolean crosss(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String table = "user";
        String whereClause = "email=?";
        String[] whereArgs = new String[] { email };
        long ins=db.delete(table, whereClause, whereArgs);
        if(ins==-1)
        {
            return false;
        }
        else
        {
            return true;

        }
        //db.execSQL("update user set isAuthorized =1 where email=?",new String[]{email});
    }
    //Retrieve data
    public Cursor allData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user",null);
        return cursor;
    }

    //Notifications list
    public Cursor notification()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        String datewithoutyear = date+"/"+(month+1);
        Log.d("123",datewithoutyear);
        Cursor cursor = db.rawQuery("Select username,email,dob from user where dob like '" +datewithoutyear + "%' ",null);
        return cursor;
    }

    //return date without year
    public String currdatewithoutyear()
    {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String datewithoutyear = date.substring(5);
        return datewithoutyear;
    }

    //Retrieve user data
    public Cursor userdata(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select username,email,dob,task from user where email = '" +email+ "' ",null);
        return cursor;
    }

    //Retrieve task of user
    public Cursor getTask(String userid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select task from user where email = '" +userid+ "' ",null);
        return cursor;
    }

    //Return completed status
    public boolean isCompleted(String userid)
    {
        boolean status;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mcursor = db.rawQuery("Select completed from user where email = ?", new String[]{userid});
        if (mcursor.moveToFirst()){
            do{
                status = mcursor.getInt(mcursor.getColumnIndex("completed")) > 0;
                return status;
            }while(mcursor.moveToNext());
        }
        return false;
    }
    public Cursor getRequestApproval()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select email from user where isAuthorized = 0 ",null);

        return cursor;
    }

    //Searching data
    public Cursor searchData(String data)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select username,email,dob,task from user where username like '%" +data+ "%' OR dob like '%" +data+ "' ",null);
        return cursor;
    }

    public boolean storeimage(Bitmap img,String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Bitmap imagetostore=img;
        byteArrayOutputStream=new ByteArrayOutputStream();
        imagetostore.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        imageintobytes=byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image",imageintobytes);
        String whereClause = "email=?";
        String whereArgs[] = {email};
        long ins;
        ins=db.update("user", contentValues, whereClause, whereArgs);
        if(ins==-1)
        {
            return false;
        }
        else
            return true;
    }
    public Cursor getimage(String mail)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select image from user where email ='"+mail+"'",null);
        return cursor;
    }
    public Cursor getProfileData(String mail){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where email ='"+mail+"'",null);
        return cursor;
    }
    public  boolean update_profile(String new_email,String old_email,String number,String dob,String address,String nation,String uid,String pan,String language,String education){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",new_email);
        contentValues.put("Phonenum",number);
        contentValues.put("dob",dob);
        contentValues.put("Address",address);
        contentValues.put("Nationality",nation);
        contentValues.put("UID",uid);
        contentValues.put("PAN",pan);
        contentValues.put("Language",language);
        contentValues.put("Education",education);
        String whereClause = "email=?";
        String whereArgs[] = {old_email};
        long ins;
        ins=db.update("user", contentValues, whereClause, whereArgs);
        if(ins==-1)
        {
            return false;
        }
        else
            return true;
    }
}
