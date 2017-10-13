package com.github.engineer.toolbox;

import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryDbAssetHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 2017-09-03.
 */

public class EngineerTheoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Subject>>, SearchView.OnQueryTextListener {
    //Binding views
    @BindView(R.id.engineering_theory_list)
    ListView mItems;
    EngineeringTheoryDbAssetHelper mEngineeringTheoryDbAssetHelper;
    //Setting fields
    private SubjectsAdapter mSubjectsAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check whether we're recreating a previously destroyed instance
        setContentView(R.layout.engineering_theory_menu);
        mEngineeringTheoryDbAssetHelper = new EngineeringTheoryDbAssetHelper(this);
        //Binding views
        ButterKnife.bind(this);
        //checking loader
        checkLoaderState();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        mSubjectsAdapter = new SubjectsAdapter(EngineerTheoryActivity.this, R.layout.engineering_theory_item, subjects);
        mItems.setAdapter(mSubjectsAdapter);
        //Setting on click listener on list view to get subject in description in detail view
        mItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subject clickedSubject = mSubjectsAdapter.getItem(i);
                Intent intent = new Intent(EngineerTheoryActivity.this, DetailSubjectActivity.class);
                intent.putExtra(getString(R.string.name_key), clickedSubject.getmName());
                intent.putExtra(getString(R.string.description_key), clickedSubject.getmDescription());
                //creating stream for image
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                clickedSubject.getmImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                intent.putExtra(getString(R.string.bitmap_key), bytes);
                startActivity(intent);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onLoaderReset(Loader<List<Subject>> loader) {
        updateAdapter(new ArrayList<Subject>());
    }

    //Method to check if the loader is initialized
    private void checkLoaderState() {
        boolean isInitialized = getSupportLoaderManager().hasRunningLoaders();
        if (!isInitialized) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            getLoaderManager().restartLoader(0, null, this).forceLoad();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.engineering_theory_menu, menu);
        // Associate searchable configuration with the SearchView, and setting listener
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(this);
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onQueryTextSubmit(String query) {
        updateAdapter(mEngineeringTheoryDbAssetHelper.getQueryData(query));
        return false;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onQueryTextChange(String newText) {
            updateAdapter(mEngineeringTheoryDbAssetHelper.getQueryData(newText));
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEngineeringTheoryDbAssetHelper.close();
    }

}
