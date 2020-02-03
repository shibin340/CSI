package com.example.csi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csi.R;
import com.example.csi.model;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVHolder> {
    ArrayList<model> arrayList;
    public Context context;
    public Cursor cursor;
    public RVAdapter(Cursor cursor, Context context)
    {
        this.context=context;
        this.cursor=cursor;
    }
    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eachrow,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVHolder holder, int position) {
        if(!cursor.moveToPosition(position))
        {
            return;
        }
        String imgname=cursor.getString(cursor.getColumnIndex("imagename"));
        //Bitmap img=cursor.getBlob(1);
        final byte[] imageBytes=cursor.getBlob(cursor.getColumnIndex("image"));
        Bitmap bitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
        holder.imageName.setText(imgname);
        holder.img.setImageBitmap(bitmap);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,FullScreenImage.class);
                intent.putExtra("imginbytes",imageBytes);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public static class RVHolder extends RecyclerView.ViewHolder
    {
        TextView imageName;
        ImageView img;
        CardView cardView;

        public RVHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            imageName=itemView.findViewById(R.id.imgname);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }
}
