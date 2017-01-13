package com.android.java.miss.tmdbmovies;

import android.app.Application;
import com.android.java.miss.tmdbmovies.network.ApiManager;
import com.facebook.stetho.Stetho;
import javax.inject.Inject;

public class MoviesApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        appComponent = AppComponent.Initializer.init(this);
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
