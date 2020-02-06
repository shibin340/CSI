package com.example.csi;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Timesheet_Adapter extends RecyclerView.Adapter<Timesheet_Adapter.Data_view_holder> {

    private Context mcontext;
    private Cursor mcursor;
    public Timesheet_Adapter(Context context, Cursor cursor)
    {
        mcontext=context;
        mcursor=cursor;
    }
    @NonNull
    @Override
    public Data_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view=inflater.inflate(R.layout.sheetdata,parent,false);
        return new Data_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Data_view_holder holder, int position) {
        String User="username";
        if(!mcursor.moveToPosition(position))
        {
            return;
        }
        final String username=mcursor.getString(mcursor.getColumnIndex(User));
        final String task=mcursor.getString(mcursor.getColumnIndex("task"));
        holder.txt.setText("Username:- "+username);
        holder.txt1.setText("Task assigned:- "+task);
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class Data_view_holder extends RecyclerView.ViewHolder{
        public TextView txt,txt1;
        public Data_view_holder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.user_txt);
            txt1=itemView.findViewById(R.id.task_txt);
        }
    }
}