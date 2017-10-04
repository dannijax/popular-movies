package com.example.danijax.popularmovies.movieslist.ui.movieslist;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by danijax on 8/10/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MoviesListViewTest {

    @Rule
    public ActivityTestRule<MoviesListView> moviesListViewActivityTestRule =
            new ActivityTestRule<>(MoviesListView.class);

}