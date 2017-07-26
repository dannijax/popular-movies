package com.example.danijax.popularmovies.movieslist.injection.components;

import com.example.danijax.popularmovies.movieslist.injection.module.MovieDbApiModule;
import com.example.danijax.popularmovies.movieslist.injection.module.NetworkModule;
import com.example.danijax.popularmovies.movieslist.injection.module.RepositoryModule;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListActivity;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by danijax on 7/26/17.
 */
@Singleton
@Component(modules = {MovieDbApiModule.class, NetworkModule.class, RepositoryModule.class})
public interface MovieDbComponent {

    void inject(MoviesListActivity moviesListView);
}
