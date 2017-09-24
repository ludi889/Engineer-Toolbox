package com.github.engineer.toolbox;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by Damian on 2017-09-18.
 */

public class App extends Application {

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