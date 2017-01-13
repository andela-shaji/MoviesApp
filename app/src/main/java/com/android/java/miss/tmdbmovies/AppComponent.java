package com.android.java.miss.tmdbmovies;

import com.android.java.miss.tmdbmovies.fragments.TopRatedMoviesFragment;
import com.android.java.miss.tmdbmovies.network.NetworkModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AppModule.class, NetworkModule.class })
public interface AppComponent {

    void inject(MoviesApp moviesApp);

    void inject(TopRatedMoviesFragment topRatedMoviesFragment);

    MainActivityComponent plus(MainModule module);

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
