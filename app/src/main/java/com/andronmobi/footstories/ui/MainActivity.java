package com.andronmobi.footstories.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.andronmobi.footstories.AppExecutors;
import com.andronmobi.footstories.R;
import com.andronmobi.footstories.common.Status;
import com.andronmobi.footstories.db.entity.StoryEntity;
import com.andronmobi.footstories.db.entity.TopEntity;
import com.andronmobi.footstories.repository.TopsRepository;
import com.andronmobi.footstories.viewmodel.TopsViewModel;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopsViewModel viewModel = ViewModelProviders.of(this).get(TopsViewModel.class);
        subscribeUi(viewModel);
    }

    private void subscribeUi(TopsViewModel viewModel) {
        viewModel.getTops().observe(this, resource -> {
            Log.d(TAG, "live data onChanged status " + resource.status);
            if (resource.status == Status.SUCCESS) {
                for (TopEntity top : resource.data) {
                    Log.d(TAG, "top: " + top.toString());
                    Log.d(TAG, "top type " + top.getType());
                    Log.d(TAG, "top title " + top.getTitle());
                    Log.d(TAG, "top size " + top.getStories().size());
                    for (StoryEntity story : top.getStories()) {
                        Log.d(TAG, "url " + story.getFullUrl());
                    }
                }
            }
        });
    }
}
