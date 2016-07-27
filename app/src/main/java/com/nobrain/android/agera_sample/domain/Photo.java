package com.nobrain.android.agera_sample.domain;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Photo {

    public static TypeAdapter<Photo> typeAdapter(Gson gson) {
        return new AutoValue_Photo.GsonTypeAdapter(gson);
    }

    @Nullable
    public abstract String id();

    @Nullable
    public abstract String owner();

    @Nullable
    public abstract String secret();

    @Nullable
    public abstract String server();

    public abstract long farm();

    @Nullable
    public abstract String title();

    public abstract long ispublic();

    public abstract long isfriend();

    public abstract long isfamily();
}
