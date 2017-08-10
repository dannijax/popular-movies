package com.example.danijax.popularmovies.movieslist.data.repository;

import com.example.danijax.popularmovies.BuildConfig;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.model.ResponseWrapper;
import com.example.danijax.popularmovies.movieslist.data.network.ApiClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by danijax on 7/26/17.
 */

public class MoviesRepository  implements Repository<Movies>{

    private ApiClient apiClient;

    public MoviesRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<List<Movies>> getAll() {
        return apiClient.getMovies(BuildConfig.API_KEY)
                .map(new Function<ResponseWrapper<Movies>, List<Movies>>() {
                    @Override
                    public List<Movies> apply(@NonNull ResponseWrapper<Movies> moviesResponseWrapper) throws Exception {
                        return moviesResponseWrapper.getResults();
                    }
                });
    }

    @Override
    public Observable<Movies> get(long id) {
        return apiClient.getMovie(id, BuildConfig.API_KEY);
    }
}
