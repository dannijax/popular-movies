package com.example.danijax.popularmovies.movieslist.injection.module;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.network.ApiClient;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module(includes = MovieDbApiModule.class)
public class RepositoryModule {
    @Provides
    @Singleton
    public Repository<Movies> moviesRepository(ApiClient apiClient) {
        return new MoviesRepository(apiClient);
    }
}
