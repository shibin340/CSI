package com.example.csi;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Permission_Adapter extends  RecyclerView.Adapter<Permission_Adapter.permission_holder>{


    private Context mcontext;
    private Cursor mcursor;
    public Permission_Adapter(Context context, Cursor cursor){
        mcontext=context;
        mcursor=cursor;
    }
    @NonNull
    @Override
    public permission_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view=inflater.inflate(R.layout.each_approval,parent,false);
        return new permission_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull permission_holder holder, int position) {

            String User="email";
            final DatabaseHelper db=new DatabaseHelper(mcontext);
        if(!mcursor.moveToPosition(position))
        {
            return;
        }
        final String temp=mcursor.getString(mcursor.getColumnIndex(User));
        holder.approve_name.setText(temp);
        holder.tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.tickk(temp))
                {
                    Toast.makeText(mcontext, "Permission granted ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(mcontext, "Failed to  grant permission", Toast.LENGTH_SHORT).show();

                }
            }
        });
        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.crosss(temp))
                {
                    Toast.makeText(mcontext, "Permission denied ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(mcontext, "User is on hold ..some error occured ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class permission_holder extends RecyclerView.ViewHolder{
        TextView approve_name;
        Button tick,cross;
        public permission_holder(@NonNull View itemView) {
            super(itemView);
            approve_name=itemView.findViewById(R.id.approve_name);
            tick=itemView.findViewById(R.id.tick);
            cross=itemView.findViewById(R.id.cross);
        }
    }
}
