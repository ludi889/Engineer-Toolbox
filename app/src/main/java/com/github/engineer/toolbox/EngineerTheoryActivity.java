package com.github.engineer.toolbox;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryContract.EngineeringTheoryEntry;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Damian on 2017-09-03.
 */

public class EngineerTheoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int SUBJECTS_LOADER = 0;
    //Binding views
    @BindView(R.id.engineering_theory_list)
    ListView itemsListView;
    //Setting fields
    private EngineerTheoryCursorAdapter mEngineeringTheoryCursorAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engineering_theory_menu);
        //Binding views
        ButterKnife.bind(this);
        //setting adapter on list view
        mEngineeringTheoryCursorAdapter = new EngineerTheoryCursorAdapter(this, null, 0);
        itemsListView.setAdapter(mEngineeringTheoryCursorAdapter);
        //Setting on click listener on list view to get subject in description in detail view
        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = mEngineeringTheoryCursorAdapter.getSubjectName();
                String description = mEngineeringTheoryCursorAdapter.getSubjectDescription();
                Bitmap bitmap = mEngineeringTheoryCursorAdapter.getBitmap();

                Intent intent = new Intent(EngineerTheoryActivity.this, DetailSubjectActivity.class);
                intent.putExtra("name_key", name);
                intent.putExtra("description_key", description);
                intent.putExtra("bitmap_key", bitmap);
                startActivity(intent);


//getting data

            }
        });
        //initialization of loader
        getLoaderManager().initLoader(SUBJECTS_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.engineer_theory_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                InsertDummySubject();
                break;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllSubjects();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //development method to validate database operations
    private void InsertDummySubject() {
        Bitmap drawableBitmap = getBitmap(R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        drawableBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();


        ContentValues values = new ContentValues();
        values.put(EngineeringTheoryEntry.COLUMN_SUBJECT_NAME, "Subject");
        values.put(EngineeringTheoryEntry.COLUMN_SUBJECT_DESCRIPTION, "Subject description");
        values.put(EngineeringTheoryEntry.COLUMN_SUBJECT_IMAGE, byteArray);

        getContentResolver().insert(EngineeringTheoryEntry.CONTENT_URI, values);
    }

    //development method to validate database operations
    private void deleteAllSubjects() {
        getContentResolver().delete(EngineeringTheoryEntry.CONTENT_URI, null, null);
    }

    //development method to validate database operations
    private Bitmap getBitmap(int drawableRes) {
        Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                EngineeringTheoryEntry._ID,
                EngineeringTheoryEntry.COLUMN_SUBJECT_NAME,
                EngineeringTheoryEntry.COLUMN_SUBJECT_DESCRIPTION,
                EngineeringTheoryEntry.COLUMN_SUBJECT_IMAGE
        };
        return new CursorLoader(this,
                EngineeringTheoryEntry.CONTENT_URI,
                projection,
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mEngineeringTheoryCursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mEngineeringTheoryCursorAdapter.swapCursor(null);

    }
}
