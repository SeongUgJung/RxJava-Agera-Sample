package com.nobrain.android.agera_sample.domain;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class RecentPhoto {

    public static TypeAdapter<RecentPhoto> typeAdapter(Gson gson) {
        return new AutoValue_RecentPhoto.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract Photos photos();
}
