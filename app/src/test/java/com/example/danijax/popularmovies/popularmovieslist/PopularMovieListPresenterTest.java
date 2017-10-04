package com.example.danijax.popularmovies.popularmovieslist;

import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListPresenter;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListView;
import com.example.danijax.popularmovies.movieslist.util.schedulers.ImmediateScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PopularMovieListPresenterTest {

    private static final Movie BATMAN_MOVIE =  new Movie("The killing joke");
    private List<Movie> mMovieList = new ArrayList<>(Arrays.asList(
            new Movie("Iron man is a big fool"), new Movie("Batman is a legend"),
            new Movie("Superman is no super man")));

    private MoviesListPresenter moviesListPresenter;

    @Mock
    private Repository<Movie> moviesRepository;

    @Mock
    MoviesListView moviesListView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        moviesListPresenter = new MoviesListPresenter(moviesRepository, new ImmediateScheduler());
        moviesListPresenter.attach(moviesListView);
    }

    @Test
    public void get_all_movies_loads_successfully() throws Exception {
        when(moviesRepository.getAll()).thenReturn(Observable.just(mMovieList));
        moviesListPresenter.getAllMovies();
        verify(moviesListView).showLoadingUi(true);
        verify(moviesListView).loadMovies(mMovieList);
        verify(moviesListView).showLoadingUi(false);
    }

    @Test
    public void get_all_movies_returns_empty_list() throws Exception {
        List<Movie> emptyList = new ArrayList<>();
        when(moviesRepository.getAll()).thenReturn(Observable.just(emptyList));
        moviesListPresenter.getAllMovies();
        verify(moviesListView).showLoadingUi(true);
        verify(moviesListView).showEmptyMovies();
        verify(moviesListView).showLoadingUi(false);
    }

    @Test
    public void get_all_movies_failed() throws Exception {
        when(moviesRepository.getAll()).thenReturn(
                Observable.<List<Movie>>error(new Throwable("error")));
        moviesListPresenter.getAllMovies();
        verify(moviesListView).showLoadingUi(true);
        verify(moviesListView).showError();
        verify(moviesListView).showLoadingUi(false);
    }


}
