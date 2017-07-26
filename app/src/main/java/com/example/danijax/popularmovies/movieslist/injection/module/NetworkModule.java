package com.example.danijax.popularmovies.movieslist.injection.module;


import android.content.Context;

import com.example.danijax.popularmovies.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


@Module(includes = ContextModule.class)
public class NetworkModule {
    @Provides
    @Singleton
    public OkHttpClient OkhttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", BuildConfig.API_KEY);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new StethoInterceptor())
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

    }

    @Provides
    @Singleton
    public Cache cache(File file) {
        return new Cache(file, 10 * 1024 * 1024);

    }

    @Provides
    @Singleton
    public File file(Context context) {
        return new File(context.getCacheDir(), "moviesdb_cache");

    }
}
