package com.github.engineer.toolbox;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.github.engineer.toolbox.data.engineeringtheorydata.EngineeringTheoryDbAssetHelper;

import java.util.List;

/**
 * Created by Damian on 2017-09-24.
 */

class SubjectLoader extends AsyncTaskLoader<List<Subject>> {
    SubjectLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public List<Subject> loadInBackground() {
        EngineeringTheoryDbAssetHelper engineeringTheoryDbAssetHelper = new EngineeringTheoryDbAssetHelper(getContext());
        return engineeringTheoryDbAssetHelper.getData();
    }
}
