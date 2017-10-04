package com.example.danijax.popularmovies.movieslist.ui.moviedetails;

import android.text.TextUtils;

import com.example.danijax.popularmovies.movieslist.data.model.DefaultObserver;
import com.example.danijax.popularmovies.movieslist.data.model.Movie;
import com.example.danijax.popularmovies.movieslist.data.repository.Repository;
import com.example.danijax.popularmovies.movieslist.ui.base.BaseView;
import com.example.danijax.popularmovies.movieslist.util.schedulers.BaseScheduler;

import org.w3c.dom.Text;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;



public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    private Repository<Movie> moviesRepository;

    private CompositeDisposable disposable;

    private MovieDetailsContract.View movieDetailsView;

    private BaseScheduler scheduler;

    @Inject
    public MovieDetailsPresenter(Repository<Movie> moviesRepository, BaseScheduler scheduler) {
        this.moviesRepository = moviesRepository;
        this.scheduler = scheduler;
        disposable = new CompositeDisposable();
    }

    @Override
    public void attach(BaseView view) {
        this.movieDetailsView = (MovieDetailsContract.View) view;

    }

    @Override
    public void detach() {
        this.movieDetailsView = null;
        unsubscribe();
    }


    private void unsubscribe() {
        disposable.dispose();
    }

    @Override
    public void getMovieDetails(long movieId) {
        movieDetailsView.enableShareButton(false);
        movieDetailsView.showLoadingUi(true);
        subscribe(moviesRepository.get(movieId));
    }

    private void subscribe(Observable<Movie> observable) {
        Observable<Movie> moviesObservable = observable
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui());
       disposable.add( moviesObservable.subscribeWith(new MovieObserver()));

    }

    @Override
    public void loadMovie(Movie movie) {
        if (movie.getTitle() == null && movie.getOverview() == null)
            movieDetailsView.showEmptyMovieDetails();

        else
            movieDetailsView.loadMovie(movie);
    }

    @Override
    public void shareMovie(Movie movie) {
        movieDetailsView.enableShareButton(false);
        movieDetailsView.shareMovie(movie);
    }

    private class MovieObserver extends DefaultObserver<Movie> {
        @Override
        public void onNext(@NonNull Movie movie) {
            loadMovie(movie);
            movieDetailsView.showLoadingUi(false);
            movieDetailsView.enableShareButton(true);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            movieDetailsView.showLoadingUi(false);
            movieDetailsView.showError();
            movieDetailsView.enableShareButton(true);
        }

        @Override
        public void onComplete() {
        }
    }
}
