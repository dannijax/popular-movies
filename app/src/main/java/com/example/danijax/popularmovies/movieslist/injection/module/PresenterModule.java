package com.example.danijax.popularmovies.movieslist.injection.module;


import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListPresenter;
import com.example.danijax.popularmovies.movieslist.util.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RepositoryModule.class, SchedulerModule.class})
public class PresenterModule {

    @Provides
    @Singleton
    public MoviesListPresenter moviesListPresenter(Repository<Movies> moviesRepository,
            SchedulerProvider schedulerProvider) {
        return new MoviesListPresenter(moviesRepository, schedulerProvider);
    }
}
