package com.github.engineer.toolbox;

import android.content.Intent;
import android.graphics.BitmapFactory;
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
    //Binding views
    @BindView(R.id.subject_name_detail_view)
    TextView mSubjectName;
    @BindView(R.id.subject_description_detail_view)
    TextView mSubjectDescription;
    @BindView(R.id.subject_image_detail_view)
    ImageView mSubjectImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_subject);
        ButterKnife.bind(this);

        Intent intent = getIntent();


        mSubjectName.setText(intent.getStringExtra(getString(R.string.name_key)));
        mSubjectDescription.setText(intent.getStringExtra(getString(R.string.description_key)));
        //getting image byte array, and decode
        byte[] bytes = intent.getByteArrayExtra(getString(R.string.bitmap_key));
        mSubjectImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

    }

}
