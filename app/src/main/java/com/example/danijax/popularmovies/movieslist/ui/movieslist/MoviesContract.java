package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.ui.base.BasePresenter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;

import java.util.List;

/**
 * Created by danijax on 7/26/17.
 */

public interface MoviesContract {

    interface View extends BaseView {
        void loadMovies(List<Movies> movies);
        void showLoadingUi(boolean show);
        void showError();
        void showEmptyMovies();

    }

    interface Presenter extends BasePresenter {
        void loadMovies(List<Movies> movies);
        void getAllMovies();
    }
}
