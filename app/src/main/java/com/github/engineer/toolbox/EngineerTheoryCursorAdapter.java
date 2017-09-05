package com.github.engineer.toolbox;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryContract.EngineeringTheoryEntry;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 2017-09-04.
 */

class EngineerTheoryCursorAdapter extends CursorAdapter {
    //Binding views
    @BindView(R.id.subject_image_item_view)
    ImageView subjectImageView;
    @BindView(R.id.subject_name_item_view)
    TextView subjectNameView;
    @BindView(R.id.subject_description_item_view)
    TextView subjectDescriptionView;
    private String subjectName;
    private String subjectDescription;
    private Bitmap bitmap;

    EngineerTheoryCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.engineering_theory_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Bitmap bitmap;
        ButterKnife.bind(this, view);
        //getting data indexes
        int subjectImageIndex = cursor.getColumnIndex(EngineeringTheoryEntry.COLUMN_SUBJECT_IMAGE);
        int subjectNameIndex = cursor.getColumnIndex(EngineeringTheoryEntry.COLUMN_SUBJECT_NAME);
        int subjectDescriptionIndex = cursor.getColumnIndex(EngineeringTheoryEntry.COLUMN_SUBJECT_DESCRIPTION);
        //getting data from index
        final byte[] subjectImageByte = cursor.getBlob(subjectImageIndex);
        subjectName = cursor.getString(subjectNameIndex);
        subjectDescription = cursor.getString(subjectDescriptionIndex);
        //operating with image
        if (subjectImageByte != null) {
            bitmap = BitmapFactory.decodeByteArray(subjectImageByte, 0, subjectImageByte.length);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }
        setBitmap(bitmap);
        //populate views
        subjectImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 256, 256, false));
        subjectNameView.setText(subjectName);
        subjectDescriptionView.setText(subjectDescription);
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}



