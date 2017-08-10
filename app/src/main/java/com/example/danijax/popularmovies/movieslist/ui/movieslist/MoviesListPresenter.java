package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import android.util.Log;

import com.example.danijax.popularmovies.movieslist.data.model.DefaultObserver;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by danijax on 7/26/17.
 */

public class MoviesListPresenter implements MoviesContract.Presenter {

    private static final String TAG = MoviesListPresenter.class.getName();

    private MoviesContract.View movieListView;

    private Repository moviesRepository;

    private CompositeDisposable disposable;

    public MoviesListPresenter(Repository moviesRepository) {
        this.moviesRepository = moviesRepository;
        disposable = new CompositeDisposable();
    }

    @Override
    public void loadMovies(List<Movies> movies) {
        movieListView.showLoadingUi(true);
        movieListView.loadMovies(movies);
    }

    @Override
    public void getAllMovies() {
        subscribeToMoviesList(moviesRepository.getAll());

    }

    @Override
    public void attach(BaseView view) {
        this.movieListView = (MoviesContract.View) view;

    }

    @Override
    public void dettach() {
        this.movieListView = null;
        unsubscribe();
    }

    private void unsubscribe() {
        disposable.dispose();
    }

    private void subscribeToMoviesList(Observable<List<Movies>> mo) {
        Observable<List<Movies>> observable = mo
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(observable
        .subscribeWith(new MoviesListObserver()));

    }

    private class MoviesListObserver extends DefaultObserver<List<Movies>> {
        @Override
        public void onNext(@NonNull List<Movies> movies) {
            loadMovies(movies);
            movieListView.showLoadingUi(false);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            movieListView.showLoadingUi(false);
            movieListView.showError();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            movieListView.showLoadingUi(false);
        }
    }
}
