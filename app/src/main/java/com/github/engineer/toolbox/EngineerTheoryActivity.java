package com.github.engineer.toolbox;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 2017-09-03.
 */

public class EngineerTheoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Subject>> {
    private static final int SUBJECTS_LOADER = 0;
    Cursor cursor;
    //Binding views
    @BindView(R.id.engineering_theory_list)
    ListView itemsListView;
    //Setting fields
    private SubjectsAdapter subjectsAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engineering_theory_menu);
        //Binding views
        ButterKnife.bind(this);
        //checking loader
        checkLoaderState();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.engineering_theory_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.info_button:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Subject>> onCreateLoader(int i, Bundle bundle) {
        return new SubjectLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Subject>> loader, List<Subject> subjects) {
        updateAdapter(subjects);

    }

    private void updateAdapter(List<Subject> subjects) {
        subjectsAdapter = new SubjectsAdapter(EngineerTheoryActivity.this, R.layout.engineering_theory_item, subjects);
        itemsListView.setAdapter(subjectsAdapter);
        //Setting on click listener on list view to get subject in description in detail view
        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subject clickedSubject = subjectsAdapter.getItem(i);
                Intent intent = new Intent(EngineerTheoryActivity.this, DetailSubjectActivity.class);
                intent.putExtra("name_key", clickedSubject.getmName());
                intent.putExtra("description_key", clickedSubject.getmDescription());
                intent.putExtra("bitmap_key", clickedSubject.getmImage());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onLoaderReset(Loader<List<Subject>> loader) {
        updateAdapter(null);

    }

    private void checkLoaderState() {
        boolean isInitalized = getSupportLoaderManager().hasRunningLoaders();
        if (!isInitalized) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            getLoaderManager().restartLoader(0, null, this).forceLoad();
        }
    }
}
