package com.github.engineer.toolbox;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 2017-09-24.
 */

public class SubjectsAdapter extends ArrayAdapter<Subject> {
    //Binding views
    @BindView(R.id.subject_image_item_view)
    ImageView subjectImageView;
    @BindView(R.id.subject_name_item_view)
    TextView subjectNameView;
    @BindView(R.id.subject_description_item_view)
    TextView subjectDescription;
    private ArrayList<Subject> subjects;

    public SubjectsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Subject> subjects) {
        super(context, resource, subjects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.engineering_theory_item, parent, false);
        }
        ButterKnife.bind(this, listItemView);
        Subject subject = getItem(position);
        subjectNameView.setText(subject.getmName());
        subjectDescription.setText(subject.getmDescription());
        subjectImageView.setImageBitmap(subject.getmImage());
        return listItemView;
    }
}



