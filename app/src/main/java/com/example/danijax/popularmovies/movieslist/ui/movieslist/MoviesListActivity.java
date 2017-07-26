package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.MovieDbApplication;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.ui.adapter.MoviesAdapter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoviesListActivity extends BaseActivity implements MoviesContract.View {

    private MoviesContract.Presenter moviesPresenter;
    private Unbinder unbinder;
    private MoviesAdapter moviesAdapter;

    @Inject
    MoviesRepository moviesRepository;

    @BindView(R.id.movies_list)
    RecyclerView mMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        unbinder = ButterKnife.bind(this);
        ((MovieDbApplication) getApplication()).getMovieDbComponent().inject(this);
        setupPresenter();
        setupRecyclerView();

    }

    private void setupRecyclerView() {
        moviesAdapter = new MoviesAdapter(new ArrayList<Movies>());
        moviesAdapter.setMovieSelectedListener(new MoviesAdapter.MovieSelectedListener() {
            @Override
            public void onClick(View view, int position) {
                showMovieDetails(moviesAdapter.getMovie(position));
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMoviesList.setLayoutManager(linearLayoutManager);
        mMoviesList.setAdapter(moviesAdapter);
    }

    private void showMovieDetails(Movies movie) {

    }

    private void setupPresenter() {
        moviesPresenter = new MoviesListPresenter(moviesRepository);
        moviesPresenter.attach(this);
        moviesPresenter.getAllMovies();
    }

    @Override
    public void loadMovies(List<Movies> movies) {
        moviesAdapter.addMovies(movies);
    }

    @Override
    public void showLoadingUi() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmptyMovies() {

    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        moviesPresenter.dettach();
        super.onDestroy();
    }
}
