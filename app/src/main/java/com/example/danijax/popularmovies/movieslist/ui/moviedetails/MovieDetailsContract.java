package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.ui.base.BasePresenter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;

public interface MovieDetailsContract {

    interface View extends BaseView{
        void loadMovie(Movie movie);
        void showLoadingUi(boolean show);
        void showError();
        void showEmptyMovieDetails();
        void shareMovie(Movie movie);
        void enableShareButton(boolean enable);

    }

    interface Presenter extends BasePresenter {
        void getMovieDetails(long movieId);
        void loadMovie(Movie movieId);
        void shareMovie(Movie movieId);
    }
}
