package com.example.csi.ui;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
import com.example.csi.SliderAdapterExample;
import com.example.csi.TimeSheet;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class CSIfragment extends Fragment{
    TextView txt_gal;
    RVAdapter mAdapter2;
    SliderView sliderView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_csi, container, false);
        ImageH db1;
        db1=new ImageH(getActivity().getApplicationContext());
        txt_gal = root.findViewById(R.id.txt_gal);
        sliderView = root.findViewById(R.id.imageSlider);
        //final RecyclerView recyclerView2=root.findViewById(R.id.rcv4);
        //slider image

        final SliderAdapterExample adapter = new SliderAdapterExample(getActivity().getApplicationContext());
        adapter.setCount(5);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setSliderAnimationDuration(500);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }

        });

        //gallery recycler
       /* Cursor cursor2=db1.getAllimage();
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

        }*/
        return root;
    }
}