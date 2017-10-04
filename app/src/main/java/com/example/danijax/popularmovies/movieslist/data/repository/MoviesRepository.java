package com.example.danijax.popularmovies.movieslist.data.repository;

import com.example.danijax.popularmovies.BuildConfig;
import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.data.model.ResponseWrapper;
import com.example.danijax.popularmovies.movieslist.data.network.ApiClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class MoviesRepository  implements Repository<Movie>{

    private ApiClient apiClient;

    @Inject
    public MoviesRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<List<Movie>> getAll() {
        return apiClient.getMovies(BuildConfig.API_KEY)
                .map(new Function<ResponseWrapper<Movie>, List<Movie>>() {
                    @Override
                    public List<Movie> apply(@NonNull ResponseWrapper<Movie> moviesResponseWrapper) throws Exception {
                        return moviesResponseWrapper.getResults();
                    }
                });
    }

    @Override
    public Observable<Movie> get(long id) {
        return apiClient.getMovie(id, BuildConfig.API_KEY);
    }
}
