package com.github.engineer.toolbox;

import android.graphics.Bitmap;

/**
 * Created by Damian on 2017-09-24.
 */

public class Subject {
    private String mName;
    private String mDescription;
    private Bitmap mImage;

    public Subject(String name, String description, Bitmap image) {
        mName = name;
        mDescription = description;
        mImage = image;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
