package com.andronmobi.footstories.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.andronmobi.footstories.FootApp;
import com.andronmobi.footstories.common.Resource;
import com.andronmobi.footstories.db.entity.TopEntity;

import java.util.List;

public class TopsViewModel extends AndroidViewModel {

    private final MediatorLiveData<Resource<List<TopEntity>>> mObservableTops;

    public TopsViewModel(@NonNull Application application) {
        super(application);

        mObservableTops = new MediatorLiveData<>();
        LiveData<Resource<List<TopEntity>>> tops = ((FootApp) application).getTopsRepository().loadTops();
        mObservableTops.addSource(tops, resource -> mObservableTops.postValue(resource));
    }

    public LiveData<Resource<List<TopEntity>>> getTops() {
        return mObservableTops;
    }
}
