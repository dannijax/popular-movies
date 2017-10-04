package com.example.danijax.popularmovies.movieslist.data.network;

import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.data.model.ResponseWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by danijax on 7/26/17.
 */

public interface ApiClient {

    @GET("discover/movie")
    Observable<ResponseWrapper<Movie>> getMovies(@Query("api_key") String apiKey);

    @GET("movie/{movieId}")
    Observable<Movie> getMovie(@Path("movieId") long movieId, @Query("api_key") String apiKey);


}
