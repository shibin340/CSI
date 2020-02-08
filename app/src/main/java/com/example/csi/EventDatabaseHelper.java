package com.example.csi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class EventDatabaseHelper extends SQLiteOpenHelper {
    public EventDatabaseHelper(@Nullable Context context) {
        super(context, "Event.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table events(event text,description text,date text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists events");
    }

    //Insertion
    public boolean addEvents(String event,String description,String date){
        SQLiteDatabase db= this.getWritableDatabase();
        String new_title = event.replace("'","''");
        StringTokenizer stringTokenizer = new StringTokenizer(date,"-",false);
        String final_date,final_month;
        String datee = stringTokenizer.nextToken();
        String month = stringTokenizer.nextToken();
        String year = stringTokenizer.nextToken();
        if(datee.length() == 1)
        {
            final_date = "0"+datee;
        }
        else
            final_date = datee;
        if(month.length() == 1)
        {
            final_month = "0"+month;
        }
        else
            final_month = month;
        String addDate = year+"-"+final_month+"-"+final_date;
        ContentValues contentValues = new ContentValues();
        contentValues.put("event",new_title);
        contentValues.put("description",description);
        contentValues.put("date",addDate);
        long ins = db.insert("events",null,contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }

    //Retrieve data
    public Cursor EventsData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String datewithoutyear = date.substring(5);
        Cursor cursor = db.rawQuery("Select distinct(event),description,date from events where date >= '"+date+"' order by date asc" ,null);
        //String datee = cursor.getString(cursor.getColumnIndex("date"));
        return cursor;
    }

    public boolean chkevent(String title)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean status = false;
        String new_title = title.replace("'","''");
        Cursor cursor = db.rawQuery("Select date from events where event = '"+new_title+"'" ,null);
        if(cursor.getCount()==0)
        {
            status = true;
        }
        return status;
    }
}
