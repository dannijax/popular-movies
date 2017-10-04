package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.ui.base.BasePresenter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;

import java.util.List;


public interface MoviesContract {

    interface View extends BaseView {
        void loadMovies(List<Movie> movies);
        void showLoadingUi(boolean show);
        void showError();
        void showEmptyMovies();
    }

    interface Presenter extends BasePresenter {
        void getAllMovies();
//        void showMovie(Movie movie);
    }
}
