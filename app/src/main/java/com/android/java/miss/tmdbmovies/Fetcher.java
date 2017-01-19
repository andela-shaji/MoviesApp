package com.android.java.miss.tmdbmovies;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Fetcher {

    private final Param param;

    @Inject
    public Fetcher(Param param) {
        this.param = param;
    }

    public void fetch() {
        param.retrieve();
    }
}
