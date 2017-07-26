package com.example.danijax.popularmovies.popularmovieslist;

import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesContract;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesListPresenter;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.verify;

/**
 * Created by danijax on 7/26/17.
 */

public class PopularMoviesListTest {


    private MoviesListPresenter popularMoviesListPresentePresenter;

    @Mock
    private MoviesContract.View popularMoviesListView;

    @Mock
    private Repository moviesRepository;


    public void setUp() {
        MockitoAnnotations.initMocks(this);
        popularMoviesListPresentePresenter = new MoviesListPresenter(popularMoviesListView, moviesRepository);
    }


    @Test
    public void loadMoviesFromRepository() throws Exception {

        fail("Not yet implemented");
    }

    public void loadMoviesInView() throws Exception {
        fail("Not yet implemented");
    }


}
