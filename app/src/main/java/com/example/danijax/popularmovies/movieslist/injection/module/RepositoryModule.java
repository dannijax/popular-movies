package com.example.danijax.popularmovies.movieslist.injection.module;

import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public MoviesRepository moviesRepository() {
        return new MoviesRepository();
    }
}
