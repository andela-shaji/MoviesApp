package com.android.java.miss.tmdbmovies;

import dagger.Module;
import dagger.Provides;

@Module
class MainModule {

    @Provides DataExtractor provideDataExtractor() {
        return new DataExtractor();
    }

}
