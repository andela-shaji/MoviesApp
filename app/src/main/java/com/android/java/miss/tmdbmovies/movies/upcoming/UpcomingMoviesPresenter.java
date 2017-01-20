package com.android.java.miss.tmdbmovies.movies.upcoming;

import com.android.java.miss.tmdbmovies.model.Movie;
import com.android.java.miss.tmdbmovies.model.MovieResponse;
import com.android.java.miss.tmdbmovies.network.ApiManager;
import java.util.ArrayList;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static com.android.java.miss.tmdbmovies.utils.Constants.API_KEY;

class UpcomingMoviesPresenter {

    private ApiManager apiManager;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private UpcomingScreen screen;

    UpcomingMoviesPresenter(ApiManager apiManager, UpcomingScreen screen) {
        this.apiManager = apiManager;
        this.screen = screen;
    }

    void fetchMovies() {
        final Observable<MovieResponse> movieObservable = apiManager.getUpcomingMovies(API_KEY);
        compositeSubscription.add(movieObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<MovieResponse>() {
                @Override
                public void call(MovieResponse movieResponse) {
                    if (movieResponse == null
                        || movieResponse.getResults() == null
                        || movieResponse.getResults().size() == 0) {
                        screen.displayEmptyState();
                    }
                    ArrayList<Movie> movies = movieResponse.getResults();
                    screen.onMoviesResponse(movies);
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    screen.displayErrorState();
                }
            }));
    }

    void unbind() {
        compositeSubscription.unsubscribe();
    }
}