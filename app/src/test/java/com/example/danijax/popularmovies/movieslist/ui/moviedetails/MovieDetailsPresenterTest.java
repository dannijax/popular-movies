package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.network.ApiClient;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by danijax on 8/9/17.
 */
public class MovieDetailsPresenterTest {

    private static final Movies HULK = new Movies("The Incredible hulk");

    private MovieDetailsPresenter movieDetailsPresenter;

    private Repository moviesRepository;

    @Mock
    private MovieDetailsContract.View movieDetailsListView;

    @Mock
    ApiClient apiClient;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        moviesRepository = new MoviesRepository(apiClient);
        movieDetailsPresenter = new MovieDetailsPresenter(moviesRepository);
        movieDetailsPresenter.attach(movieDetailsListView);
    }

    @Test
    public void getMovieFromApiReturnsMoviewithId() {
        when (apiClient.getMovie(1l, "")).thenReturn(Observable.just(HULK));
        TestObserver<Movies> moviesTestObserver = TestObserver.create();
        moviesRepository.get(1l).subscribe(moviesTestObserver);
        moviesTestObserver.awaitTerminalEvent();
        moviesTestObserver.assertNoErrors();
    }

    @Test
    public void loadMoviesFromRepository() throws Exception {
        //fail("Not yet implemented");
        when(apiClient.getMovie(1, "")).thenReturn(Observable.just(HULK));
        TestObserver<List<Movies>> listTestObserver = TestObserver.create().create();
        moviesRepository.getAll().subscribe(listTestObserver);
        listTestObserver.awaitTerminalEvent();
        listTestObserver.assertNoErrors();
        List<Object> moviesList = listTestObserver.getEvents().get(0);

    }

    @Test
    public void loadMoviesInView() throws Exception {
        movieDetailsPresenter.loadMovie(HULK);
        verify(movieDetailsListView).loadMovie(HULK);
        verify(movieDetailsListView).showLoadingUi(true);
    }

}