package com.example.danijax.popularmovies.movieslist.ui.base;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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

    public void makeSnackBarMessage(String errorMessage) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}
