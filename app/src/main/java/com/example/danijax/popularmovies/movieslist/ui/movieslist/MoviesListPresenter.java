package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import com.example.danijax.popularmovies.movieslist.data.model.DefaultObserver;
import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;
import com.example.danijax.popularmovies.movieslist.util.schedulers.BaseScheduler;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;


public class MoviesListPresenter implements MoviesContract.Presenter {

    private static final String TAG = MoviesListPresenter.class.getName();

    private MoviesContract.View movieListView;

    private Repository<Movie> moviesRepository;

    private CompositeDisposable disposable;

    private BaseScheduler scheduler;


    @Inject
    public MoviesListPresenter(Repository<Movie> moviesRepository, BaseScheduler scheduler) {
        this.moviesRepository = moviesRepository;
        this.scheduler = scheduler;
        disposable = new CompositeDisposable();
    }

    private void loadMovies(List<Movie> movies) {
        if (movies.isEmpty()) movieListView.showEmptyMovies();

        else movieListView.loadMovies(movies);
    }

    @Override
    public void getAllMovies() {
        movieListView.showLoadingUi(true);
        subscribeToMoviesList(moviesRepository.getAll());

    }

    @Override
    public void attach(BaseView view) {
        this.movieListView = (MoviesContract.View) view;

    }

    @Override
    public void detach() {
        this.movieListView = null;
        unsubscribe();
    }

    private void unsubscribe() {
        disposable.dispose();
    }

    private void subscribeToMoviesList(Observable<List<Movie>> mo) {
        Observable<List<Movie>> observable = mo
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui());
        disposable.add(observable
        .subscribeWith(new MoviesListObserver()));

    }

    private class MoviesListObserver extends DefaultObserver<List<Movie>> {
        @Override
        public void onNext(@NonNull List<Movie> movies) {
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
            //movieListView.showLoadingUi(false);
        }
    }
}
