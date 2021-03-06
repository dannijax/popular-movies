package com.example.danijax.popularmovies.movieslist.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by danijax on 7/26/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies;

    private MovieSelectedListener mMovieSelectedListener;

    public MoviesAdapter(@NonNull List<Movie> movies) {
        this.mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_item_layout,
                parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void addMovies(@NonNull List<Movie> movies) {
        this.mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setMovieSelectedListener(MovieSelectedListener movieSelectedListener) {
        this.mMovieSelectedListener = movieSelectedListener;
    }

    public Movie getMovie(int position) {
        return mMovies.get(position);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private Unbinder unbinder;

        @BindView(R.id.movie_cover)
        ImageView movieCover;

        @BindView(R.id.movie_title)
        TextView movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

        void bindMovie(@NonNull Movie movie) {
            Picasso.with(itemView.getContext())
                    .load(Constants.IMAGE_BASE_URL + movie.getPosterPath())
                    .into(movieCover);

            movieTitle.setText(movie.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMovieSelectedListener.onClick(v, getAdapterPosition());
                }
            });
        }
    }

    public interface MovieSelectedListener {
        void onClick(View view, int position);
    }
}
