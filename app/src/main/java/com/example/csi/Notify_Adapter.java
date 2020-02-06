package com.example.csi;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Notify_Adapter extends RecyclerView.Adapter<Notify_Adapter.Notify_view_holder> {
    private Context mcontext;
    private Cursor mcursor;
    public Notify_Adapter(Context context, Cursor cursor){
        mcontext=context;
        mcursor=cursor;
    }

    @NonNull
    @Override
    public Notify_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view=inflater.inflate(R.layout.notify_item,parent,false);
        return new Notify_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notify_view_holder holder, int position) {
        String User="username";
        String mail="email";
        String dob="dob";
        if(!mcursor.moveToPosition(position))
        {
            return;
        }
        String username=mcursor.getString(mcursor.getColumnIndex(User));
        String email=mcursor.getString(mcursor.getColumnIndex(mail));
        String dateofbday=mcursor.getString(mcursor.getColumnIndex(dob));
        holder.tvname.setText("Happy birthday "+username+"!!");
        holder.tvdob.setText(dateofbday);
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class Notify_view_holder extends RecyclerView.ViewHolder{
        TextView tvname,tvmail,tvdob;
        public Notify_view_holder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvname);
            tvdob=itemView.findViewById(R.id.tvdob);
        }
    }
}

