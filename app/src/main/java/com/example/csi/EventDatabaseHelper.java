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
        ContentValues contentValues = new ContentValues();
        contentValues.put("event",event);
        contentValues.put("description",description);
        contentValues.put("date",date);
        long ins = db.insert("events",null,contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }

    //Retrieve data
    public Cursor EventsData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String todaysDate = date+"/"+(month+1)+"/"+year;
        Cursor cursor = db.rawQuery("Select distinct(event),description,date from events where date >= '"+todaysDate+"' order by date asc " ,null);
        return cursor;
    }

    public boolean chkevent(String title)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean status = false;
        Cursor cursor = db.rawQuery("Select date from events where event = '"+title+"' order by date asc " ,null);
        if(cursor.getCount()==0)
        {
            status = true;
        }
        return status;
    }
}
