package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;

import java.util.List;

/**
 * Created by danijax on 7/26/17.
 */

public interface MoviesContract {

    interface View {
        void loadMovies(List<Movies> movies);

        void showLoadingUi();
    }

    interface Presenter {
        void loadMovies(List<Movies> movies);
    }
}
