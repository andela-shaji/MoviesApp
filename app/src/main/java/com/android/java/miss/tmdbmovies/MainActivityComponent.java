package com.android.java.miss.tmdbmovies;

import dagger.Subcomponent;

@Subcomponent(modules = MainModule.class )
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
