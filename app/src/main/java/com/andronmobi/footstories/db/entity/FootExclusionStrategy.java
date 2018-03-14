package com.andronmobi.footstories.db.entity;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class FootExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(FootExclude.class) != null;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
