package com.android.java.miss.tmdbmovies;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final MoviesApp app;

    AppModule(MoviesApp app) {
        this.app = app;
    }
    @Provides
    @ApplicationContext Context provideApplicationContext() {
        return app.getApplicationContext();
    }
}
