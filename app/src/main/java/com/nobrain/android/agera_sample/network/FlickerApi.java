package com.nobrain.android.agera_sample.network;

import com.nobrain.android.agera_sample.BuildConfig;
import com.nobrain.android.agera_sample.domain.RecentPhoto;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FlickerApi {

    private final Retrofit retrofit;

    @Inject
    public FlickerApi(Retrofit retrofit) {this.retrofit = retrofit;}

    public Call<RecentPhoto> getRecentPhoto(int page, int perPage) {
        return retrofit.create(Api.class).getRecentPhoto(page, perPage);
    }

    protected interface Api {
        @GET("?format=json&nojsoncallback=1&method=flickr.interestingness.getList&api_key=" + BuildConfig.FLICKER_API_KEY)
        Call<RecentPhoto> getRecentPhoto(@Query("page") int page, @Query("per_page") int perPage);
    }
}
