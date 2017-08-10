package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import com.example.danijax.popularmovies.movieslist.data.model.DefaultObserver;
import com.example.danijax.popularmovies.movieslist.data.model.Movies;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;
import com.example.danijax.popularmovies.movieslist.ui.movieslist.MoviesContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by danijax on 8/9/17.
 */

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private Repository moviesRepository;

    private CompositeDisposable disposable;

    private MovieDetailsContract.View movieDetailsView;

    public MovieDetailsPresenter(Repository moviesRepository) {
        this.moviesRepository = moviesRepository;
        disposable = new CompositeDisposable();
    }

    @Override
    public void attach(BaseView view) {
        this.movieDetailsView = (MovieDetailsContract.View) view;

    }

    @Override
    public void dettach() {
        this.movieDetailsView = null;
        unsubscribe();
    }


    private void unsubscribe() {
        disposable.dispose();
    }

    @Override
    public void getMovieDetails(long movieId) {
        subscribe(moviesRepository.get(movieId));
    }

    private void subscribe(Observable<Movies> observable) {
        Observable<Movies> moviesObservable = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
       disposable.add( moviesObservable.subscribeWith(new MovieObserver()));

    }

    @Override
    public void loadMovie(Movies movie) {
        movieDetailsView.loadMovie(movie);
    }

    private class MovieObserver extends DefaultObserver<Movies> {
        @Override
        public void onNext(@NonNull Movies movies) {
            loadMovie(movies);
            movieDetailsView.showLoadingUi(false);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            movieDetailsView.showLoadingUi(false);
            movieDetailsView.showError();
        }

        @Override
        public void onComplete() {
            movieDetailsView.showLoadingUi(false);
        }
    }
}
