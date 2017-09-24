package com.github.engineer.toolbox.data.engineeringtheorydata;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.github.engineer.toolbox.App;
import com.github.engineer.toolbox.R;

/**
 * Created by Damian on 2017-09-03.
 */

public final class EngineeringTheoryContract {
    //String for content authority
    public static final String CONTENT_AUTHORITY = "com.github.engineer.toolbox";
    //String for base URI
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //Cons for table path
    public static final String PATH_SUBJECTS = "subjects";
    //The MIME type of the whole list of Engineering Theory subject
    public static final String CONTENT_LIST_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUBJECTS;
    //The MIME type for the a single subject of Engineering Theory
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SUBJECTS;

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private EngineeringTheoryContract() {
    }

    public static final class EngineeringTheoryEntry implements BaseColumns {
        //String for complete URI
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SUBJECTS);
        //String for table name
        public static final String TABLE_NAME = App.getContext().getResources().getString(R.string.table_name);
        //String for unique ID (int)
        public static final String _ID = BaseColumns._ID;
        //String for name of subject of entry (String)
        public static final String COLUMN_SUBJECT_NAME = "subject";
        //String for description (String)
        public static final String COLUMN_SUBJECT_DESCRIPTION = "description";
        //String for image (BLOB)
        public static final String COLUMN_SUBJECT_IMAGE = "image";
    }


}
