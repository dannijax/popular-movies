package com.example.danijax.popularmovies.movieslist;

import android.app.Application;

import com.example.danijax.popularmovies.movieslist.injection.components.DaggerMovieDbComponent;
import com.example.danijax.popularmovies.movieslist.injection.components.MovieDbComponent;
import com.example.danijax.popularmovies.movieslist.injection.module.ContextModule;
import com.example.danijax.popularmovies.movieslist.injection.module.RepositoryModule;
import com.facebook.stetho.Stetho;


public class MovieDbApplication extends Application {

    private MovieDbComponent movieDbComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        movieDbComponent = DaggerMovieDbComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public MovieDbComponent getMovieDbComponent() {
        return movieDbComponent;
    }
}
