package com.example.danijax.popularmovies.movieslist.injection.module;


import com.example.danijax.popularmovies.movieslist.util.schedulers.BaseScheduler;
import com.example.danijax.popularmovies.movieslist.util.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SchedulerModule {

    @Provides
    @Singleton
    public BaseScheduler baseScheduler() {
        return new SchedulerProvider();
    }
}
