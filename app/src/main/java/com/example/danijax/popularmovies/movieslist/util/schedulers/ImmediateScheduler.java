package com.example.danijax.popularmovies.movieslist.util.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class ImmediateScheduler implements BaseScheduler {
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }
}
