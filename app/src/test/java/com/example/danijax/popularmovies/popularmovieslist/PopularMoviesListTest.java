package com.example.danijax.popularmovies.popularmovieslist;

import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.model.ResponseWrapper;
import com.example.danijax.popularmovies.movieslist.data.network.ApiClient;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesContract;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by danijax on 7/26/17.
 */

public class PopularMoviesListTest {

    private List<Movies> moviesList = new ArrayList<>(Arrays.asList(
            new Movies("Iron man is a big fool"), new Movies("Batman is a legend"),
            new Movies("Superman is no super man")));

    private ResponseWrapper<Movies> responseWrapper = new ResponseWrapper<>();

    private MoviesListPresenter popularMoviesListPresenter;

    @Mock
    private MoviesContract.View popularMoviesListView;

    Repository moviesRepository;

    @Mock
    ApiClient apiClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        moviesRepository = new MoviesRepository(apiClient);
        popularMoviesListPresenter = new MoviesListPresenter(moviesRepository);
        popularMoviesListPresenter.attach(popularMoviesListView);
        responseWrapper.setResults(moviesList);
    }


    @Test
    public void loadMoviesFromRepository() throws Exception {
        //fail("Not yet implemented");
        when(apiClient.getMovies("")).thenReturn(Observable.just(responseWrapper));
        TestObserver<List<Movies>> testSubscriber = TestObserver.create().create();
        moviesRepository.getAll().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        List<Object> moviesList = testSubscriber.getEvents().get(0);
       // Movies movies = (Movies) moviesList.get(0);
        //assertEquals("Movie title", movies.getTitle(), "Iron man is a big fool");
        System.out.println("moviesList = " + moviesList);

    }

    @Test
    public void loadMoviesInView() throws Exception {
        popularMoviesListPresenter.loadMovies(moviesList);
        verify(popularMoviesListView).loadMovies(moviesList);
        verify(popularMoviesListView).showLoadingUi(true);
    }

    @Test
    public void getMoviesFromApiReturnsListOfMovies() {
        when(apiClient.getMovies("")).thenReturn(Observable.just(responseWrapper));
        TestObserver<List<Movies>> testSubscriber = TestObserver.create().create();
        moviesRepository.getAll().subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();

    }


}
