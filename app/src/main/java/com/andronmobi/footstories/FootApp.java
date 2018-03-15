package com.andronmobi.footstories;

import android.app.Application;

import com.andronmobi.footstories.api.LequipeWebservice;
import com.andronmobi.footstories.db.FootDb;
import com.andronmobi.footstories.db.entity.FootExclusionStrategy;
import com.andronmobi.footstories.repository.TopsRepository;
import com.andronmobi.footstories.util.LiveDataCallAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FootApp extends Application {

    private AppExecutors mAppExecutors;
    private LequipeWebservice mWebservice;
    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();

        GsonBuilder b = new GsonBuilder().setExclusionStrategies(new FootExclusionStrategy());
        Gson gson = b.create();
        mWebservice = new Retrofit.Builder()
                .baseUrl("http://app.francefootball.fr/json/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(LequipeWebservice.class);
    }

    public FootDb getDatabase() {
        return FootDb.getInstance(this);
    }

    public TopsRepository getTopsRepository() {
        return TopsRepository.getInstance(getDatabase(), mAppExecutors, mWebservice);
    }
}
