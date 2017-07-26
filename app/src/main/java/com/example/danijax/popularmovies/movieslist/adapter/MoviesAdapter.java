package com.example.danijax.popularmovies.movieslist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by danijax on 7/26/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movies> mMovies;

    private MovieSelectedListener mMovieSelectedListener;

    public MoviesAdapter(@NonNull List<Movies> movies) {
        this.mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout,
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

        void bindMovie(@NonNull Movies movies) {
            Picasso.with(itemView.getContext())
                    .load(movies.getPosterPath())
                    .into(movieCover);
            movieTitle.setText(movies.getTitle());
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
