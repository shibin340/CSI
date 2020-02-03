package com.example.csi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.csi.model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ImageH extends SQLiteOpenHelper {

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageintobytes;
    public static final int MAX_FILE_SIZE = 100 * 1024;

    public ImageH(@Nullable Context context) {
        super(context, "gallery.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table gallery(imagename text,image BLOB )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists gallery");
    }

    public boolean storeimage(model obj)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Bitmap imagetostore=obj.getImage();
        byteArrayOutputStream=new ByteArrayOutputStream();
        imagetostore.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        imageintobytes=byteArrayOutputStream.toByteArray();
        ContentValues contentValues=new ContentValues();
        contentValues.put("imagename",obj.getName());
        contentValues.put("image",imageintobytes);
        long ans=db.insert("gallery",null,contentValues);
        if(ans!=0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Cursor getAllimage()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select image,imagename from gallery ",null);
        return cursor;
    }
}
