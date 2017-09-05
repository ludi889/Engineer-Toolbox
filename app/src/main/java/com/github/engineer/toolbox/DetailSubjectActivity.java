package com.github.engineer.toolbox;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 2017-09-05.
 */

public class DetailSubjectActivity extends AppCompatActivity {
    @BindView(R.id.subject_name_detail_view)
    TextView subjectName;
    @BindView(R.id.subject_description_detail_view)
    TextView subjectDescription;
    @BindView(R.id.subject_image_detail_view)
    ImageView subjectImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_subject);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        subjectName.setText(intent.getStringExtra("name_key"));
        subjectDescription.setText(intent.getStringExtra("description_key"));
        subjectImage.setImageBitmap((Bitmap) intent.getParcelableExtra("bitmap_key"));


    }
}
