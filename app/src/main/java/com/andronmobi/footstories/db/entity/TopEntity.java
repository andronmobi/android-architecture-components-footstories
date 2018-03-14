package com.andronmobi.footstories.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "top")
public class TopEntity {

    // Room fields
    @PrimaryKey(autoGenerate = true)
    @FootExclude
    private int id;

    // Retrofit fields
    @Ignore
    @SerializedName("items")
    private List<FootstoryEntity> stories;

    // Retrofit and Room fields
    @SerializedName("suffixe")
    private String type;
    @SerializedName("titre")
    private String title;

    // TODO move to interface
    public List<FootstoryEntity> getStories() {
        return stories;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    // Not for the interface
    public void setStories(List<FootstoryEntity> stories) {
        this.stories = stories;
    }
}
