package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.MovieDbApplication;
import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.ui.adapter.MoviesAdapter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseActivity;
import com.example.danijax.popularmovies.movieslist.ui.moviedetails.MovieDetailsView;
import com.example.danijax.popularmovies.movieslist.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoviesListView extends BaseActivity implements MoviesContract.View {

    private Unbinder unbinder;
    private MoviesAdapter moviesAdapter;

    @Inject
    MoviesListPresenter moviesListPresenter;

    @BindView(R.id.movies_list)
    RecyclerView mMoviesList;

    @BindView(R.id.progress)
    ProgressBar progressBar;

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
        moviesAdapter = new MoviesAdapter(new ArrayList<Movie>());
        moviesAdapter.setMovieSelectedListener(new MoviesAdapter.MovieSelectedListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = moviesAdapter.getMovie(position);
                showMovieDetails(movie.getId(), movie.getTitle(), view);
            }
        });
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2
                , StaggeredGridLayoutManager.VERTICAL);
        mMoviesList.setLayoutManager(layoutManager);
        mMoviesList.setAdapter(moviesAdapter);
    }

    private void showMovieDetails(long id, String title, View view) {
        Intent intent = new Intent(this, MovieDetailsView.class);
        intent.putExtra(Constants.MOVIE_ID, id);
        intent.putExtra(Constants.MOVIE_TITLE, title);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, view, getString(R.string.movies_transition));
        startActivity(intent, options.toBundle());
    }

    private void setupPresenter() {
        moviesListPresenter.attach(this);
        moviesListPresenter.getAllMovies();
    }

    @Override
    public void loadMovies(List<Movie> movies) {
        moviesAdapter.addMovies(movies);
    }

    @Override
    public void showLoadingUi(boolean show) {
        if (show) progressBar.setVisibility(View.VISIBLE);

        else progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showError() {
        makeSnackBarMessage("An error has occurred");
    }

    @Override
    public void showEmptyMovies() {
        makeSnackBarMessage("No movies available");
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        moviesListPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
