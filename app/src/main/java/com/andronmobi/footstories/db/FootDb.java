package com.andronmobi.footstories.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.andronmobi.footstories.db.dao.StoryDao;
import com.andronmobi.footstories.db.dao.TopDao;
import com.andronmobi.footstories.db.entity.StoryEntity;
import com.andronmobi.footstories.db.entity.TopEntity;

@Database(entities = {TopEntity.class, StoryEntity.class}, version = 1)
public abstract class FootDb extends RoomDatabase {

    public static final String DATABASE_NAME = "foot-db";
    private static FootDb sInstance;

    abstract public TopDao topDao();
    abstract public StoryDao storyDao();

    public static FootDb getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (FootDb.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static FootDb buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, FootDb.class, DATABASE_NAME).build();
    }
}
