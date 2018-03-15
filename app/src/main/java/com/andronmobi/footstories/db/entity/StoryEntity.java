package com.andronmobi.footstories.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "stories",
        foreignKeys = {
                @ForeignKey(entity = TopEntity.class,
                        parentColumns = "type",
                        childColumns = "topType",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "topType")
        })
public class StoryEntity {

    // Room fields
    @PrimaryKey(autoGenerate = true)
    @FootExclude
    private int id;
    @FootExclude
    private String topType;

    // Retrofit and Room fields
    @SerializedName("actif")
    public boolean isActif;
    @SerializedName("fullUrl")
    public String fullUrl;
    @SerializedName("id")
    public String storyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopType() {
        return topType;
    }

    public void setTopType(String topType) {
        this.topType = topType;
    }

    // TODO move to interface
    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
