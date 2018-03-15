package com.andronmobi.footstories.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.andronmobi.footstories.AppExecutors;
import com.andronmobi.footstories.api.ApiResponse;
import com.andronmobi.footstories.api.LequipeWebservice;
import com.andronmobi.footstories.common.Resource;
import com.andronmobi.footstories.db.FootDb;
import com.andronmobi.footstories.db.entity.FootExclusionStrategy;
import com.andronmobi.footstories.db.entity.StoryEntity;
import com.andronmobi.footstories.db.entity.TopEntity;
import com.andronmobi.footstories.util.LiveDataCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopsRepository {

    private static final String TAG = "TopsRepository";

    private static TopsRepository sInstance;

    private final AppExecutors appExecutors;
    private final LequipeWebservice webservice;
    private final FootDb footDb;

    public TopsRepository(FootDb database, AppExecutors appExecutors, LequipeWebservice webservice) {
        this.appExecutors = appExecutors;
        this.footDb = database;
        this.webservice = webservice;
    }

    public static TopsRepository getInstance(FootDb database, AppExecutors executors, LequipeWebservice webservice) {
        if (sInstance == null) {
            synchronized (TopsRepository.class) {
                if (sInstance == null) {
                    sInstance = new TopsRepository(database, executors, webservice);
                }
            }
        }
        return sInstance;
    }

    public LiveData<Resource<List<TopEntity>>> loadTops() {

        return new NetworkBoundResource<List<TopEntity>, List<TopEntity>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<TopEntity> tops) {
                footDb.topDao().insertAll(tops);
                for (TopEntity top : tops) {
                    //Log.d(TAG, "------- write top title -------" + top.getTitle());
                    for (StoryEntity story : top.getStories()) {
                        story.setTopType(top.getType()); // TODO should be added to plus.json on the server
                    }
                    footDb.storyDao().insertAll(top.getStories());
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<TopEntity> data) {
                return true; // TODO if we don't have data
            }

            @NonNull
            @Override
            protected LiveData<List<TopEntity>> loadFromDb() {
                MutableLiveData<List<TopEntity>> data = new MutableLiveData();
                appExecutors.diskIO().execute(() -> {
                    // Load data from DB in IO thread
                    List<TopEntity> tops = footDb.topDao().loadAllTopsSync();
                    for (TopEntity top : tops) {
                        //Log.d(TAG, "------- read top title -------" + top.getTitle());
                        List<StoryEntity> stories = footDb.storyDao().loadStoriessSync(top.getType());
                        top.setStories(stories);
                    }
                    data.postValue(tops);
                });
                return data;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<TopEntity>>> createCall() {
                return webservice.getTops();
            }
        }.asLiveData();
    }
}
