package com.example.danijax.popularmovies.movieslist.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by danijax on 7/26/17.
 */

public class ResponseWrapper<T> {

    private Meta meta;
    @SerializedName("results")
    private List<T> results;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
