package com.example.danijax.popularmovies.movieslist.data.network;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.model.ResponseWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by danijax on 7/26/17.
 */

public interface ApiClient {

    @GET("/discover/movies")
    Observable<ResponseWrapper<Movies>> getMovies();


}
