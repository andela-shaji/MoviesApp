package com.android.java.miss.tmdbmovies.network;

import android.content.Context;
import com.android.java.miss.tmdbmovies.ApiKey;
import com.android.java.miss.tmdbmovies.ApplicationContext;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

import static com.android.java.miss.tmdbmovies.utils.Constants.API_KEY;

@Module
public class NetworkModule {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    @Provides OkHttpClient provideOkhttpClient() {
        return new OkHttpClient().newBuilder()
            .addInterceptor(new StethoInterceptor()).build();
    }

    @Provides @Singleton Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build();
    }

    @Provides @Singleton ApiManager provideApiManager(Retrofit retrofit) {
        return retrofit.create(ApiManager.class);
    }

    @Provides @Singleton @ApiKey String provideApiKey() {
        return "5ab68c282365772dba538bd0db9f5fda";
    }

    @Provides @Singleton Picasso providePicasso(@ApplicationContext Context context) {
        return Picasso.with(context);
    }
}
