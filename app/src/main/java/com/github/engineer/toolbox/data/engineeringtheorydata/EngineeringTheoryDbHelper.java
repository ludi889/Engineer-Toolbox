package com.github.engineer.toolbox.data.engineeringtheorydata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryContract.EngineeringTheoryEntry;

/**
 * Created by Damian on 2017-09-03.
 */

public class EngineeringTheoryDbHelper extends SQLiteOpenHelper {
    //String for database name
    private static final String DATABASE_NAME = "engineeringTheory.db";
    //int for database version
    private static final int DATABASE_VESRION = 1;

    EngineeringTheoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VESRION);
    }

    /**
     * Calling on create of DB
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //String for proper subjects table creation
        String SQL_SUBJECTS_TABLE_CREATE = "CREATE TABLE " + EngineeringTheoryEntry.TABLE_NAME + " ("
                + EngineeringTheoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EngineeringTheoryEntry.COLUMN_SUBJECT_NAME + " TINY TEXT NOT NULL, "
                + EngineeringTheoryEntry.COLUMN_SUBJECT_DESCRIPTION + " TEXT, "
                + EngineeringTheoryEntry.COLUMN_SUBJECT_IMAGE + " BLOB);";
        //executing creation of database
        sqLiteDatabase.execSQL(SQL_SUBJECTS_TABLE_CREATE);

    }

    /**
     * Calling on upgrade of DB
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //nothing to do
    }
}
