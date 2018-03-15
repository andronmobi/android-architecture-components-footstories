package com.andronmobi.footstories.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.andronmobi.footstories.db.entity.StoryEntity;
import com.andronmobi.footstories.db.entity.TopEntity;

import java.util.List;

@Dao
public interface TopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TopEntity top);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TopEntity> tops);

    @Query("SELECT * FROM tops WHERE id = :topId")
    LiveData<TopEntity> loadTop(int topId);

    @Query("SELECT * FROM tops")
    LiveData<List<TopEntity>> loadAllTops();

    @Query("SELECT * FROM tops")
    List<TopEntity> loadAllTopsSync();
}
