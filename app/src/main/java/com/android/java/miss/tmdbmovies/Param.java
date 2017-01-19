package com.android.java.miss.tmdbmovies;

import android.util.Log;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Param {

    public String name;

    @Inject
    public Param() {
        this.name = "Test";
    }

    public void retrieve() {
        name = "Test";
    }
}
