package com.nobrain.android.agera_sample.domain;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class Photos {
    public static TypeAdapter<Photos> typeAdapter(Gson gson) {
        return new AutoValue_Photos.GsonTypeAdapter(gson);
    }

    public abstract int page();

    public abstract int pages();

    public abstract int perpage();

    public abstract int total();

    @Nullable
    public abstract List<Photo> photo();
}
