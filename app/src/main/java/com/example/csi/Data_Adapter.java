package com.example.csi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Data_Adapter extends RecyclerView.Adapter<Data_Adapter.Data_view_holder> {

    private Context mcontext;
    private Cursor mcursor;
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

        String User="username";
        if(!mcursor.moveToPosition(position))
        {
            return;
        }
        final String username = mcursor.getString(mcursor.getColumnIndex(User));
        final String email = mcursor.getString(mcursor.getColumnIndex("email"));
        holder.bt_direc.setText(username);
        holder.bt_direc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,UserData.class);
                intent.putExtra("FileName",email);
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
        public Button bt_direc;
        public Data_view_holder(@NonNull View itemView) {
            super(itemView);
            bt_direc=itemView.findViewById(R.id.bt_direc);
        }
    }
}