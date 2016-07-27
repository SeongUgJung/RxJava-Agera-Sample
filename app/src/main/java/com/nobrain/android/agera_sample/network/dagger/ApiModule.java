package com.nobrain.android.agera_sample.network.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    Gson provideGsonParser() {
        return new GsonBuilder().registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
                .create();
    }

}
