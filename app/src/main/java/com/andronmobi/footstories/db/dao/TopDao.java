package com.andronmobi.footstories.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.andronmobi.footstories.db.entity.TopEntity;

import java.util.List;

@Dao
public abstract class TopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(TopEntity top);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<TopEntity> tops);

    @Query("SELECT * FROM tops WHERE id = :topId")
    public abstract LiveData<TopEntity> loadTop(int topId);

    @Query("SELECT * FROM tops")
    public abstract LiveData<List<TopEntity>> loadAllTops();
}
