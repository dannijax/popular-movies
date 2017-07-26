package com.example.danijax.popularmovies.movieslist.injection.module;

import com.example.danijax.popularmovies.BuildConfig;
import com.example.danijax.popularmovies.movieslist.data.network.ApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danijax on 7/26/17.
 */
@Module(includes = NetworkModule.class)
public class MovieDbApiModule {

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    public ApiClient apiClient(Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }
}
