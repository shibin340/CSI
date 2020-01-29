package com.example.csi.ui;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csi.DatabaseHelper;
import com.example.csi.EventDatabaseHelper;
import com.example.csi.Events_Adapter;
import com.example.csi.ImageH;
import com.example.csi.Notify_Adapter;
import com.example.csi.R;
import com.example.csi.RVAdapter;
import com.example.csi.TimeSheet;

public class CSIfragment extends Fragment{
    TextView txt_gal;
    RVAdapter mAdapter2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_csi, container, false);
        ImageH db1;
        db1=new ImageH(getActivity().getApplicationContext());
        txt_gal = root.findViewById(R.id.txt_gal);
        final RecyclerView recyclerView2=root.findViewById(R.id.rcv4);
        Cursor cursor2=db1.getAllimage();
        if(cursor2.getCount()==0)
        {
            txt_gal.setText("gallery is empty");
            recyclerView2.setVisibility(View.GONE);
        }
        else
        {
            recyclerView2.setVisibility(View.VISIBLE);
            recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
            mAdapter2=new RVAdapter(cursor2,getActivity().getApplicationContext());
            recyclerView2.setAdapter(mAdapter2);

        }
        return root;
    }
}