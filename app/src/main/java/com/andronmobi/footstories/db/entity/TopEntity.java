package com.andronmobi.footstories.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "tops",
        indices = {@Index("id"), @Index(value="type", unique=true)})
public class TopEntity {

    // Room fields
    @PrimaryKey(autoGenerate = true)
    @FootExclude
    private int id;

    // Retrofit fields
    @Ignore
    @SerializedName("items")
    private List<StoryEntity> stories;

    // Retrofit and Room fields
    @NonNull
    @SerializedName("suffixe")
    private String type;
    @SerializedName("titre")
    private String title;

    // TODO move to interface
    public List<StoryEntity> getStories() {
        return stories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Not for the interface
    public void setStories(List<StoryEntity> stories) {
        this.stories = stories;
    }
}
