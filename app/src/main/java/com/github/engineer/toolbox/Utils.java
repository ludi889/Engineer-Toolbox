package com.github.engineer.toolbox;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryDbAssetHelper;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;

/**
 * Created by Damian on 2017-10-02.
 */

public class Utils {
    private static final String NAME_KEY = "name_key";
    private static final String DESCRIPTION_KEY = "description_key";
    private static final String BITMAP_KEY = "bitmap_key";

    /**
     * Created by Damian on 2017-09-18.
     */

    //This is universal method for sending intent to engineering theory menu
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    Intent engineeringTheoryIntent(Activity callingActivity, String subjectKey) throws ClassNotFoundException {
        Intent intent = new Intent(callingActivity, DetailSubjectActivity.class);
        EngineeringTheoryDbAssetHelper mEngineeringTheoryDbAssetHelper = new EngineeringTheoryDbAssetHelper(callingActivity);
        Subject subject = mEngineeringTheoryDbAssetHelper.getSubject(subjectKey);
        intent.putExtra(NAME_KEY, subject.getmName());
        intent.putExtra(DESCRIPTION_KEY, subject.getmDescription());
        //creating stream for image
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        subject.getmImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        intent.putExtra(BITMAP_KEY, bytes);
        mEngineeringTheoryDbAssetHelper.close();
        return intent;
    }

    /**
     * This method is used to get context of the app anywhere
     */
    public static class App extends Application {

        private static WeakReference<Context> mContext;

        public static Context getContext() {
            return mContext.get();
        }

        @Override
        public void onCreate() {
            super.onCreate();
            mContext = new WeakReference<Context>(this);
        }
    }
}
