package com.example.danijax.popularmovies.movieslist.data.repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by danijax on 7/26/17.
 */

public interface Repository <T>{
    Observable<List<T>> getAll();

    Observable<T> get(String id);
}
