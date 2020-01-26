package com.example.csi;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Events_Adapter extends RecyclerView.Adapter<Events_Adapter.Events_view_holder> {
    private Context mcontext;
    private Cursor mcursor;
    public Events_Adapter(Context context, Cursor cursor){
        mcontext=context;
        mcursor=cursor;
    }

    @NonNull
    @Override
    public Events_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view=inflater.inflate(R.layout.notify_item,parent,false);
        return new Events_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Events_view_holder holder, int position) {
        String User="event";
        String mail="description";
        String dob="date";
        if(!mcursor.moveToPosition(position))
        {
            return;
        }
        String Event=mcursor.getString(mcursor.getColumnIndex(User));
        String Desc=mcursor.getString(mcursor.getColumnIndex(mail));
        String date=mcursor.getString(mcursor.getColumnIndex(dob));
        holder.tvname.setText(Event);
        holder.tvmail.setText(Desc);
        holder.tvdob.setText(date);
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class Events_view_holder extends RecyclerView.ViewHolder{
        TextView tvname,tvmail,tvdob;
        public Events_view_holder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvname);
            tvmail=itemView.findViewById(R.id.tvmail);
            tvdob=itemView.findViewById(R.id.tvdob);
        }
    }
}
