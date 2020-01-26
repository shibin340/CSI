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
import com.example.csi.Notify_Adapter;
import com.example.csi.R;
import com.example.csi.TimeSheet;

public class CSIfragment extends Fragment{
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_csi, container, false);
        return root;
    }
}