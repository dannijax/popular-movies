package com.example.danijax.popularmovies.movieslist.util.schedulers;

import io.reactivex.Scheduler;

/**
 * Created by danijax on 10/4/17.
 */

public interface BaseScheduler {

    Scheduler io();

    Scheduler ui();

    Scheduler computation();
}
