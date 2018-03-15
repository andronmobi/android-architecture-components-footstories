/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andronmobi.footstories.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.andronmobi.footstories.db.entity.StoryEntity;

import java.util.List;

@Dao
public interface StoryDao {
    @Query("SELECT * FROM stories where topType = :topType")
    LiveData<List<StoryEntity>> loadStories(int topType);

    @Query("SELECT * FROM stories where topType = :topType")
    List<StoryEntity> loadStoriessSync(int topType);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<StoryEntity> stories);
}