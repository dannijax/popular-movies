package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.ui.base.BasePresenter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;

/**
 * Created by danijax on 8/9/17.
 */

public interface MovieDetailsContract {

    interface View extends BaseView{
        void loadMovie(Movies movie);
        void showLoadingUi(boolean show);
        void showError();
        void shareMovie(Movies movies);

    }

    interface Presenter extends BasePresenter {
        void getMovieDetails(long movieId);
        void loadMovie(Movies movieId);
    }
}
