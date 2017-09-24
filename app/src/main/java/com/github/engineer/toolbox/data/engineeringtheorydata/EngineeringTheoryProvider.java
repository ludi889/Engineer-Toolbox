package com.github.engineer.toolbox.data.engineeringtheorydata;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryContract.EngineeringTheoryEntry;

/**
 * Created by Damian on 2017-09-03. At this date I don't see a reasonable way to use insert, delete and edit method other than development state, so it can be done in not efficent way
 */

public class EngineeringTheoryProvider extends ContentProvider {
    //Setting ints for uriMatcher
    private static final int SUBJECTS = 100;
    private static final int SUBJECT_ID = 101;
    //Setting uriMatcher
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //Setting static initializer
    static {
        //Add uri call for multiple row of table
        sUriMatcher.addURI(EngineeringTheoryContract.CONTENT_AUTHORITY, EngineeringTheoryContract.PATH_SUBJECTS, SUBJECTS);
        //Add uri call for single row
        sUriMatcher.addURI(EngineeringTheoryContract.CONTENT_AUTHORITY, EngineeringTheoryContract.PATH_SUBJECTS + "/#", SUBJECT_ID);
    }

    //Setting DbHelper object
    EngineeringTheoryDbAssetHelper mDbHelper;

    @Override
    public boolean onCreate() {
        //Setting Db helper method to create/open Db
        mDbHelper = new EngineeringTheoryDbAssetHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //Getting database to read
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        //Setting cursor object
        Cursor cursor;

        //Letting UriMatcher work to consider if there is single or multiple rows
        int match = sUriMatcher.match(uri);
        switch (match) {
            case SUBJECTS: // here we get whole table, don't need any projection/selection
                cursor = database.query(EngineeringTheoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SUBJECT_ID: //here we get exact row, so     we need to get proper selection
                selection = EngineeringTheoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(EngineeringTheoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        //getting notification for UI work
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SUBJECTS:
                return EngineeringTheoryContract.CONTENT_LIST_TYPE;
            case SUBJECT_ID:
                return EngineeringTheoryContract.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri + "with math " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case SUBJECTS:
                return insertItem(uri, contentValues);
            default:
                throw new IllegalArgumentException("Problem with insert" + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues contentValues) {
        //name insert
        String subjectName = contentValues.getAsString(EngineeringTheoryEntry.COLUMN_SUBJECT_NAME);
        if (subjectName == null) {
            throw new IllegalArgumentException("Subject need to have name");
        }
        //description insert
        String subjectDescription = contentValues.getAsString(EngineeringTheoryEntry.COLUMN_SUBJECT_DESCRIPTION);
        if (subjectDescription == null) {
            throw new IllegalArgumentException("Subject need to have description");
        }
        //image as byte array insert
        byte[] bitmap = contentValues.getAsByteArray(EngineeringTheoryEntry.COLUMN_SUBJECT_IMAGE);
        //getting writable database
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        //creating long for new id
        long newRowId = database.insert(EngineeringTheoryEntry.TABLE_NAME, null, contentValues);

        if (newRowId == -1) {
            Log.e("Engineering Theory Prov", "Failed to insert data" + uri);
        }
        //change notify
        getContext().getContentResolver().notifyChange(uri, null);
        //returning uri with appended id
        return ContentUris.withAppendedId(uri, newRowId);


    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsDeleted;
        //As in template, for now just for development state, and deleting test entries
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SUBJECTS:
                //delete all rows
                rowsDeleted = database.delete(EngineeringTheoryEntry.TABLE_NAME, selection, selectionArgs);
                if (rowsDeleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsDeleted;
            case SUBJECT_ID:
                selection = EngineeringTheoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(EngineeringTheoryEntry.TABLE_NAME, selection, selectionArgs);
                if (rowsDeleted != 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsDeleted;
            default:
                throw new IllegalArgumentException("Problem with delete operation" + uri);
        }

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        //As in template
        return 0;
    }
}
