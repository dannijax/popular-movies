package com.example.danijax.popularmovies.movieslist.data.repository;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by danijax on 7/26/17.
 */

public class MoviesRepository  implements Repository<Movies>{
    @Override
    public Observable<List<Movies>> getAll() {
        return null;
    }

    @Override
    public Observable<Movies> get(String id) {
        return null;
    }
}
