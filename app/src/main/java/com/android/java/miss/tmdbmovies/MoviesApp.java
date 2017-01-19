package com.android.java.miss.tmdbmovies;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MoviesApp extends Application {

  private AppComponent appComponent;
  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);

    // For you to have depedencies given to some class
    appComponent = AppComponent.Initializer.init(this);
    appComponent.inject(this);
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
