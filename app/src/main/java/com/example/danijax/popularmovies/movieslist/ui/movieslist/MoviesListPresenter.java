package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;

import java.util.List;

/**
 * Created by danijax on 7/26/17.
 */

public class MoviesListPresenter implements MoviesContract.Presenter {

    private MoviesContract.View movieListView;

    private Repository moviesRepository;

    public MoviesListPresenter(MoviesContract.View movieListView, Repository moviesRepository) {
        this.movieListView = movieListView;
        this.moviesRepository = moviesRepository;
    }

    @Override
    public void loadMovies(List<Movies> movies) {

    }
}
