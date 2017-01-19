package com.android.java.miss.tmdbmovies.movies.upcoming;

import com.android.java.miss.tmdbmovies.model.Movie;
import com.android.java.miss.tmdbmovies.model.MovieResponse;
import com.android.java.miss.tmdbmovies.network.ApiManager;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static com.android.java.miss.tmdbmovies.utils.Constants.API_KEY;

public class UpcomingMoviesPresenter {

    ApiManager apiManager;

    Picasso picasso;

    CompositeSubscription compositeSubscription = new CompositeSubscription();

    UpcomingScreen screen;

    public UpcomingMoviesPresenter(ApiManager apiManager, Picasso picasso, UpcomingScreen screen) {
        this.apiManager = apiManager;
        this.picasso = picasso;
        this.screen = screen;
    }

    void fetch() {
        final Observable<MovieResponse> movieObservable = apiManager.getUpcomingMovies(API_KEY);
        compositeSubscription.add(movieObservable.observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<MovieResponse>() {
                @Override
                public void call(MovieResponse movieResponse) {
                    ArrayList<Movie> movies = movieResponse.getResults();
                    screen.onMoviesResponse(movies);
                }
            }));
    }
}
