package com.example.danijax.popularmovies.movieslist.injection.components;

import com.example.danijax.popularmovies.movieslist.injection.module.RepositoryModule;
import com.example.danijax.popularmovies.movieslist.injection.module.SchedulerModule;
import com.example.danijax.popularmovies.movieslist.ui.moviedetails.MovieDetailsView;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class,
        SchedulerModule.class})
public interface MovieDbComponent {

    void inject(MoviesListView moviesListView);
    void inject(MovieDetailsView movieDetailsViewActivity);
}
