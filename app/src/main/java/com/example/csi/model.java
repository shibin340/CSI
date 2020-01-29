package com.example.csi;

import android.graphics.Bitmap;

public class model {

    private Bitmap image;

    public model(Bitmap image, String name) {
        this.image = image;
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String name;


}
