package com.github.engineer.toolbox;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
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
    TextView subjectName;
    @BindView(R.id.subject_description_detail_view)
    TextView subjectDescription;
    @BindView(R.id.subject_image_detail_view)
    ImageView subjectImage;
    //setting fields
    private boolean isImageFitToScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_subject);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        subjectName.setText(intent.getStringExtra("name_key"));
        subjectDescription.setText(intent.getStringExtra("description_key"));
        Bitmap bitmap = intent.getParcelableExtra("bitmap_key");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //scaling for suitable view
        Bitmap scaledBitmap = bitmapResizer(bitmap, displayMetrics.widthPixels, 768);
        subjectImage.setImageBitmap(scaledBitmap);
        subjectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    subjectImage.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    subjectImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    subjectImage.setAdjustViewBounds(false);
                }

            }
        });
    }

    public Bitmap bitmapResizer(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;

    }
}
