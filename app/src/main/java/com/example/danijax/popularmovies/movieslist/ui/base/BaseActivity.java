package com.example.danijax.popularmovies.movieslist.ui.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.danijax.popularmovies.R;

/**
 * Created by danijax on 7/26/17.
 */

public class BaseActivity extends AppCompatActivity {

    public void addFragment(Fragment fragment, FragmentTransaction fragmentTransaction,
                            int containerId) {
        fragmentTransaction
                .add(containerId, fragment, fragment.getClass().getName())
                .commit();

    }
}
