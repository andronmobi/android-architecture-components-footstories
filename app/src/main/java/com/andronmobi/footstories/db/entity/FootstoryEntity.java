package com.andronmobi.footstories.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "story",
        foreignKeys = {
                @ForeignKey(entity = TopEntity.class,
                        parentColumns = "id",
                        childColumns = "topId",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "topId")
        })
public class FootstoryEntity {

    // Room fields
    @PrimaryKey(autoGenerate = true)
    @FootExclude
    private int id;
    @FootExclude
    private int topId;

    // Retrofit and Room fields
    @SerializedName("actif")
    boolean isActif;
    @SerializedName("fullUrl")
    String fullUrl;
    @SerializedName("id")
    String storyId;

    // TODO move to interface
    public String getFullUrl() {
        return fullUrl;
    }

    public String getStoryId() {
        return storyId;
    }
}
