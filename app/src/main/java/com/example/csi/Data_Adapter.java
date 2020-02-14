package com.example.csi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;

public class Data_Adapter extends RecyclerView.Adapter<Data_Adapter.Data_view_holder> {

    private Context mcontext;
    private Cursor mcursor;
    DatabaseHelper db;
    public Data_Adapter(Context context, Cursor cursor)
    {
        mcontext=context;
        mcursor=cursor;
    }
    @NonNull
    @Override
    public Data_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view=inflater.inflate(R.layout.data_item,parent,false);
        return new Data_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Data_view_holder holder, int position) {



            db=new DatabaseHelper(mcontext);
            String User = "username";
            if (!mcursor.moveToPosition(position)) {
                return;
            }
            final String username = mcursor.getString(mcursor.getColumnIndex(User));
            final String email = mcursor.getString(mcursor.getColumnIndex("email"));
            //try block for blob exception
        try {
            Cursor cursor1=db.getimage(email);

             if((cursor1 != null) && (cursor1.getCount() > 0)) {
                if (cursor1.moveToFirst()) {
                do {
                    if (cursor1.getBlob(cursor1.getColumnIndex("image")) != null) {
                        byte[] imagebyte = cursor1.getBlob(cursor1.getColumnIndex("image"));
                         ByteArrayInputStream inputStream = new ByteArrayInputStream(imagebyte);
                         Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        holder.img_user.setImageBitmap(bitmap);
                    }
                    else {
                        holder.img_user.setBackgroundResource(R.drawable.ic_person_black_24dp);
                    }
                } while (cursor1.moveToNext());
            }
            }
            else
            {
                holder.img_user.setBackgroundResource(R.drawable.ic_person_black_24dp);
            }
            }
            catch (Exception e)
            {
                Toast.makeText(mcontext,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
            holder.txt_search_result.setText(username);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, UserData.class);
                    intent.putExtra("FileName", email);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class Data_view_holder extends RecyclerView.ViewHolder{
        //public Button bt_direc;
        public TextView txt_search_result;
        public ImageView img_user;
        public CardView cardView;
        public Data_view_holder(@NonNull View itemView) {
            super(itemView);
            txt_search_result=itemView.findViewById(R.id.txt_search_result);
            img_user=itemView.findViewById(R.id.img_user);
            cardView=itemView.findViewById(R.id.card_search_result);
            //bt_direc=itemView.findViewById(R.id.bt_direc);
        }
    }
}