package com.android.java.miss.tmdbmovies;

import com.android.java.miss.tmdbmovies.fragments.NowPlayingMoviesFragment;
import com.android.java.miss.tmdbmovies.fragments.PopularMoviesFragment;
import com.android.java.miss.tmdbmovies.fragments.TopRatedMoviesFragment;
import com.android.java.miss.tmdbmovies.fragments.UpcomingMoviesFragment;
import com.android.java.miss.tmdbmovies.movies.upcoming.UpcomingMoviesView;
import com.android.java.miss.tmdbmovies.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules =  {AppModule.class, NetworkModule.class})
public interface AppComponent {

  void inject(MoviesApp moviesApp);

  void inject(TopRatedMoviesFragment topRatedMoviesFragment);

  void inject(MainActivity mainActivity);

  void inject(PopularMoviesFragment popularMoviesFragment);

  void inject(UpcomingMoviesFragment upcomingMoviesFragment);

  void inject(NowPlayingMoviesFragment nowPlayingMoviesFragment);

  void inject(UpcomingMoviesView upcomingMoviesView);

  // Helper class
  final class Initializer {
    private Initializer() {
    }

    static AppComponent init(MoviesApp app) {
      return DaggerAppComponent.builder()
              .appModule(new AppModule(app))
              .build();
    }
  }
}
