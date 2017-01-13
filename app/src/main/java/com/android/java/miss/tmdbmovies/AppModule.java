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


    /*@ApiKey String provideApiKey() {
        return "";
    }

    @ApiUrl String provideApiUrl() {
        return "";
    }*/

    /*
    @Provides Type provideName(Need need1, @ApiKey String apiKey) {
          return new MyNewDependency(need1);
    }

    @Provides Need provideNeed() {
        return Need();
    }
    */
}
