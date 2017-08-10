package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.MovieDbApplication;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.ui.adapter.MoviesAdapter;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseActivity;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesContract;
import com.example.danijax.popularmovies.movieslist.util.Constants;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailsView extends BaseActivity implements MovieDetailsContract.View {

    private Unbinder unbinder;
    private MovieDetailsPresenter movieDetailsPresenter;

    @Inject
    MoviesRepository moviesRepository;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.movie_title)
    TextView movieTitleTextView;

    @BindView(R.id.poster)
    ImageView moviePoster;

    @BindView(R.id.overview)
    TextView overviewText;

    @BindView(R.id.share)
    ImageButton shareButton;

    private String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieTitle = getIntent().getStringExtra(Constants.MOVIE_TITLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(movieTitle);
        setSupportActionBar(toolbar);
        unbinder = ButterKnife.bind(this);
        ((MovieDbApplication) getApplication()).getMovieDbComponent().inject(this);
        setupPresenter();

    }

    private void setupPresenter() {
        movieDetailsPresenter = new MovieDetailsPresenter(moviesRepository);
        movieDetailsPresenter.attach(this);
        movieDetailsPresenter.getMovieDetails(getIntent().getLongExtra(Constants.MOVIE_ID, 0));
    }

    @Override
    public void loadMovie(Movies movie) {
        Picasso.with(this)
                .load(Constants.IMAGE_BASE_URL.concat(movie.getBackdropPath()))
                .into(moviePoster);
        movieTitleTextView.setText(movie.getTitle());
        overviewText.setText(movie.getOverview());
        shareMovie(movie);
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
    public void shareMovie(final Movies movies) {
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " );
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, movies.getOverview());
                sendIntent.putExtra(Intent.EXTRA_STREAM, Constants.IMAGE_BASE_URL.contains(movies.getBackdropPath()));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        });


    }
}
