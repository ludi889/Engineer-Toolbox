package com.github.engineer.toolbox;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.engineer.toolbox.data.EngineeringTheoryData.EngineeringTheoryContract.EngineeringTheoryEntry;

import java.io.ByteArrayOutputStream;

/**
 * Created by Damian on 2017-09-03.
 */

public class EngineerTheoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engineering_theory_menu);
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
    }

    //development method to validate database operations
    private void deleteAllSubjects() {
        int deletedRows = getContentResolver().delete(EngineeringTheoryEntry.CONTENT_URI, null, null);
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

}
