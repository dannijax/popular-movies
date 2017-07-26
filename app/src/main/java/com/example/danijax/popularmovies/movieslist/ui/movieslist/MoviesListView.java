package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danijax.popularmovies.R;
import com.example.danijax.popularmovies.movieslist.MovieDbApplication;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.MoviesRepository;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by danijax on 7/26/17.
 */

public class MoviesListView extends BaseFragment implements MoviesContract.View {

    private MoviesContract.Presenter moviesPresenter;
    private Unbinder unbinder;

    @Inject
    MoviesRepository moviesRepository;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_list_fragment, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ((MovieDbApplication) getActivity().getApplication()).getMovieDbComponent().inject(this);
//        moviesPresenter.attach(this);
//    }

    @Override
    public void onDestroy() {
        moviesPresenter.dettach();
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void loadMovies(List<Movies> movies) {

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
}
