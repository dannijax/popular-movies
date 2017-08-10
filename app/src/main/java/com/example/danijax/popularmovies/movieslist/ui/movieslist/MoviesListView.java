package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.MovieDbApplication;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.ui.adapter.MoviesAdapter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseActivity;
import com.example.danijax.popularmovies.movieslist.ui.moviedetails.MovieDetails;
import com.example.danijax.popularmovies.movieslist.util.Constants;

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
        moviesAdapter = new MoviesAdapter(new ArrayList<Movies>());
        moviesAdapter.setMovieSelectedListener(new MoviesAdapter.MovieSelectedListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MoviesListActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Movies movie = moviesAdapter.getMovie(position);
                showMovieDetails(movie.getId(), movie.getTitle(), view);
            }
        });
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mMoviesList.setLayoutManager(layoutManager);
        mMoviesList.setAdapter(moviesAdapter);
    }

    private void showMovieDetails(long id, String title, View view) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra(Constants.MOVIE_ID, id);
        intent.putExtra(Constants.MOVIE_TITLE, title);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,view, getString(R.string.movies_transition) );
        startActivity(intent, options.toBundle());
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
    public void showLoadingUi(boolean show) {
        if (show) progressBar.setVisibility(View.VISIBLE);

        else progressBar.setVisibility(View.GONE);

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

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
