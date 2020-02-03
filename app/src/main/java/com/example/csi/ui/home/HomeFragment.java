package com.example.csi.ui.home;

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
import com.example.csi.Notify_Adapter;
import com.example.csi.R;
import com.example.csi.TimeSheet;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;
    TextView textViewUser,textViewBday,textViewEvents;
    DatabaseHelper db;
    Notify_Adapter mAdapter;
    Events_Adapter mAdapter1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = new DatabaseHelper(getActivity().getApplicationContext());
        final Intent intent = getActivity().getIntent();
        final String msg = intent.getStringExtra("EXTRA_MESSAGE");
        textViewUser = root.findViewById(R.id.tvuser);
        textViewUser.setText("Welcome, "+msg);
        textViewBday = root.findViewById(R.id.tvbday);
        textViewEvents = root.findViewById(R.id.tv_events);
        final RecyclerView recyclerView=root.findViewById(R.id.rcv2);
        final RecyclerView recyclerView1=root.findViewById(R.id.rcv3);
        Cursor cursor = db.notification();
        if(cursor.getCount()==0)
        {
            textViewBday.setText("No Birthdays today!");
            recyclerView.setVisibility(View.GONE);
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter=new Notify_Adapter(getActivity(),cursor);
            recyclerView.setAdapter(mAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),
                    DividerItemDecoration.VERTICAL));
        }
        EventDatabaseHelper edb;
        edb = new EventDatabaseHelper(getActivity());
        Cursor cursor1 = edb.EventsData();
        if(cursor1.getCount()==0)
        {
            textViewEvents.setText("No upcoming Events");
            recyclerView1.setVisibility(View.GONE);
        }
        else
        {
            recyclerView1.setVisibility(View.VISIBLE);
            recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter1=new Events_Adapter(getActivity(),cursor1);
            recyclerView1.setAdapter(mAdapter1);
            recyclerView1.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),
                    DividerItemDecoration.VERTICAL));
        }
        return root;
    }
}