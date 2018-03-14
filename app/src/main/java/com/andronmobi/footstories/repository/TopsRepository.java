package com.andronmobi.footstories.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.andronmobi.footstories.AppExecutors;
import com.andronmobi.footstories.api.ApiResponse;
import com.andronmobi.footstories.api.LequipeWebservice;
import com.andronmobi.footstories.common.Resource;
import com.andronmobi.footstories.db.entity.FootExclusionStrategy;
import com.andronmobi.footstories.db.entity.TopEntity;
import com.andronmobi.footstories.util.LiveDataCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopsRepository {

    private static final String TAG = "TopsRepository";

    private final AppExecutors appExecutors;
    private final LequipeWebservice lequipeWebservice;

    // TODO delete them (workaround since there is no DB support yet)
    private List<TopEntity> topList = new ArrayList<>(0);
    private MutableLiveData<List<TopEntity>> topsFromDb = new MutableLiveData<>();

    public TopsRepository(AppExecutors appExecutors /*, LequipeWebservice lequipeWebservice*/) {
        this.appExecutors = appExecutors;

        GsonBuilder b = new GsonBuilder().setExclusionStrategies(new FootExclusionStrategy());
        Gson gson = b.create();

        this.lequipeWebservice = new Retrofit.Builder()
                .baseUrl("http://app.francefootball.fr/json/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(LequipeWebservice.class);
    }

    public LiveData<Resource<List<TopEntity>>> loadTops() {

        return new NetworkBoundResource<List<TopEntity>, List<TopEntity>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<TopEntity> tops) {
                topList = tops; // TODO since there is no DB support yet, store it in the property
//                for (TopEntity top : tops) {
//                    top.getStories();
//                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<TopEntity> data) {
                return true; // TODO if we don't have data
            }

            @NonNull
            @Override
            protected LiveData<List<TopEntity>> loadFromDb() {
                topsFromDb.setValue(topList);
                return topsFromDb;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<TopEntity>>> createCall() {
                return lequipeWebservice.getTops();
            }
        }.asLiveData();
    }
}
