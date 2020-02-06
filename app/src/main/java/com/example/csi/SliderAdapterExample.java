package com.example.csi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {
    private Context context;
    private int mCount;

    public SliderAdapterExample(Context context) {
        this.context = context;
    }
    public void setCount(int count) {
        mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder,  int position) {

        int temp=position;
        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gal_2)
                        .into(viewHolder.image);
                //viewHolder.textViewDescription.setText("Gaming Events");
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gal_3)
                        .into(viewHolder.image);
                //viewHolder.textViewDescription.setText("Highlights");
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gal_4)
                        .into(viewHolder.image);
                //viewHolder.textViewDescription.setText("Social Work");
                break;
            case 3:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gal_5)
                        .into(viewHolder.image);
                //viewHolder.textViewDescription.setText("Teacher Day");
                break;
            case 4:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gal_7)
                        .into(viewHolder.image);
                break;
               // viewHolder.textViewDescription.setText("Technical Event");
            case 5:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gal_6)
                        .into(viewHolder.image);
                break;
                //viewHolder.textViewDescription.setText("Technical Event");
            case 6:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.gal_1)
                        .into(viewHolder.image);
                break;
                //viewHolder.textViewDescription.setText("Technical Event");
        }
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView image;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}

