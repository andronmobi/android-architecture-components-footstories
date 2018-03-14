package com.andronmobi.footstories.api;

import android.arch.lifecycle.LiveData;


import com.andronmobi.footstories.db.entity.TopEntity;

import java.util.List;

import retrofit2.http.GET;

public interface LequipeWebservice {

    @GET("les_plus/plus.json")
    LiveData<ApiResponse<List<TopEntity>>> getTops();
}
