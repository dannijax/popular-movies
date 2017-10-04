package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.MovieDbApplication;
import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseActivity;
import com.example.danijax.popularmovies.movieslist.util.Constants;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailsView extends BaseActivity implements MovieDetailsContract.View {

    private Unbinder unbinder;
    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

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

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieTitle = getIntent().getStringExtra(Constants.MOVIE_TITLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(movieTitle);
        setSupportActionBar(toolbar);
        unbinder = ButterKnife.bind(this);
        ((MovieDbApplication) getApplication())
                .getMovieDbComponent()
                .inject(this);
        setupPresenter();

    }

    private void setupPresenter() {
        movieDetailsPresenter.attach(this);
        movieDetailsPresenter
                .getMovieDetails(getIntent()
                        .getLongExtra(Constants.MOVIE_ID, 0));
    }

    @Override
    public void loadMovie(Movie movie) {
        Picasso.with(this)
                .load(Constants.IMAGE_BASE_URL.concat(movie.getBackdropPath()))
                .into(moviePoster);
        movieTitleTextView.setText(movie.getTitle());
        overviewText.setText(movie.getOverview());
        mMovie = movie;

    }

    @OnClick(R.id.share)
    public void shareMovieIntent() {
        if (mMovie == null){
        }
        else
        movieDetailsPresenter.shareMovie(mMovie);
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
    public void showEmptyMovieDetails() {

    }

    @Override
    public void shareMovie(final Movie movie) {
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, movie.getOverview());
                sendIntent.putExtra(Intent.EXTRA_STREAM,
                        Constants.IMAGE_BASE_URL.contains(movie.getBackdropPath()));
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        });


    }

    @Override
    public void enableShareButton(boolean enable) {
       shareButton.setEnabled(enable);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        movieDetailsPresenter.detach();
        super.onDestroy();
    }
}
