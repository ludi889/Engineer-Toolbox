package com.github.engineer.toolbox.data.engineeringtheorydata;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.github.engineer.toolbox.App;
import com.github.engineer.toolbox.R;
import com.github.engineer.toolbox.Subject;
import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryContract.EngineeringTheoryEntry;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


/**
 * Created by Damian on 2017-09-12.
 */

public class EngineeringTheoryDbAssetHelper extends SQLiteAssetHelper {
    //String for database name
    private static final String DATABASE_NAME = "engineeringTheory.db";
    //int for database version
    private static final int DATABASE_VERSION = 1;


    public EngineeringTheoryDbAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayList<Subject> getData() {
        Bitmap bitmap;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Subject> subjects = new ArrayList<>();
        try (Cursor cursor = db.rawQuery("select * from " + EngineeringTheoryEntry.TABLE_NAME, null)) {
            while (cursor.moveToNext()) {
                byte[] bytes = cursor.getBlob(cursor.getColumnIndexOrThrow(EngineeringTheoryEntry.COLUMN_SUBJECT_IMAGE));
                if (bytes != null) {
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                } else {
                    bitmap = BitmapFactory.decodeResource(App.getContext().getResources(), R.mipmap.ic_launcher);
                }

                subjects.add(new Subject(
                        cursor.getString(cursor.getColumnIndexOrThrow(EngineeringTheoryEntry.COLUMN_SUBJECT_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(EngineeringTheoryEntry.COLUMN_SUBJECT_DESCRIPTION)), bitmap));
            }
            return subjects;
        }
    }
}
