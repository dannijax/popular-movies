package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.data.network.ApiClient;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.util.schedulers.ImmediateScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailsPresenterTest {

    private static final Movie HULK = new Movie("The Incredible hulk");

    private MovieDetailsPresenter movieDetailsPresenter;

    @Mock
    private Repository moviesRepository;

    @Mock
    private MovieDetailsContract.View movieDetailsListView;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        movieDetailsPresenter = new MovieDetailsPresenter(moviesRepository, new ImmediateScheduler());
        movieDetailsPresenter.attach(movieDetailsListView);
    }

    @Test
    public void load_movie_success() {
        when (moviesRepository.get(1l)).thenReturn(Observable.just(HULK));
        movieDetailsPresenter.getMovieDetails(1l);
        verify(movieDetailsListView).showLoadingUi(true);
        verify(movieDetailsListView).loadMovie(HULK);
        verify(movieDetailsListView).showLoadingUi(false);

    }

    @Test
    public void get_movie_details_return_empty_details() throws Exception {
        Movie EMPTY_MOVIE = new Movie();
        when(moviesRepository.get(1)).thenReturn(Observable.just(EMPTY_MOVIE));
        movieDetailsPresenter.getMovieDetails(1l);
        verify(movieDetailsListView).showLoadingUi(true);
        verify(movieDetailsListView).showLoadingUi(false);
        verify(movieDetailsListView).showEmptyMovieDetails();


    }

    @Test
    public void get_movieDetails_error() throws Exception {
        when(moviesRepository.get(1)).thenReturn(Observable.error(new Throwable("Unknown Error")));
        movieDetailsPresenter.getMovieDetails(1l);
        verify(movieDetailsListView).showLoadingUi(true);
        verify(movieDetailsListView).showLoadingUi(false);
        verify(movieDetailsListView).showError();
    }

    @Test
    public void share_empty_movie_fails() throws Exception {
        Movie movie = null;
        movieDetailsPresenter.shareMovie(movie);
        verify(movieDetailsListView).enableShareButton(false);
    }

    @Test
    public void share_button_disabled_if_movie_isNull() throws Exception {
        when(moviesRepository.get(1)).thenReturn(Completable.complete().toObservable());
        movieDetailsPresenter.getMovieDetails(1L);
        verify(movieDetailsListView).enableShareButton(false);
    }

    @Test
    public void share_button_enabled_if_movie_isNotNull() throws Exception {
        when(moviesRepository.get(1)).thenReturn(Observable.just(HULK));
        movieDetailsPresenter.getMovieDetails(1L);
        verify(movieDetailsListView).enableShareButton(true);

    }

}