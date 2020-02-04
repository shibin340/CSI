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
        this.mCount = count;
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
                        .load(R.drawable.email_profile)
                        .into(viewHolder.image);
                viewHolder.textViewDescription.setText("Gaming Events");
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/compsa-78af5.appspot.com/o/uploads%2FHighlights%2F.jpg?alt=media&token=6349dd89-ee47-4ddb-8815-b56ba5d55792")
                        .into(viewHolder.image);
                viewHolder.textViewDescription.setText("Highlights");
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/compsa-78af5.appspot.com/o/uploads%2FSocial%20Work%2FCleaning%20Of%20Ghats%20.png?alt=media&token=9fde4b12-605f-45df-aa79-6c6dc2f5778f")
                        .into(viewHolder.image);
                viewHolder.textViewDescription.setText("Social Work");
                break;
            case 3:
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/compsa-78af5.appspot.com/o/uploads%2FTeachers%20day%2FTeachers%20Day%202K19.jpg?alt=media&token=0a4e8323-553e-44d6-84d7-6d9cc8f25310")
                        .into(viewHolder.image);
                viewHolder.textViewDescription.setText("Teacher Day");

                break;
            case 4:
                Glide.with(viewHolder.itemView)
                        .load("https://firebasestorage.googleapis.com/v0/b/compsa-78af5.appspot.com/o/uploads%2FTechnical%20Events%2FCoding%20Event%20Under%20Technical%20Event.jpg?alt=media&token=a7d081cd-ce38-4f5d-ad10-b8586059dabb")
                        .into(viewHolder.image);
                viewHolder.textViewDescription.setText("Technical Event");
        }
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 5;
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

