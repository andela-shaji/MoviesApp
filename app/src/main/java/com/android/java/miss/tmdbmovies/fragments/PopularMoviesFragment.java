package com.android.java.miss.tmdbmovies.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.java.miss.tmdbmovies.MoviesApp;
import com.android.java.miss.tmdbmovies.R;
import com.android.java.miss.tmdbmovies.adapters.MoviesAdapter;
import com.android.java.miss.tmdbmovies.model.Movie;
import com.android.java.miss.tmdbmovies.model.MovieResponse;
import com.android.java.miss.tmdbmovies.network.ApiManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static com.android.java.miss.tmdbmovies.utils.Constants.API_KEY;
import static com.makeramen.roundedimageview.RoundedDrawable.TAG;


public class PopularMoviesFragment extends Fragment {
    CompositeSubscription compositeSubscription = new CompositeSubscription();
    @Inject
    ApiManager apiManager;

    @Inject
    Picasso picasso;

    public PopularMoviesFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MoviesApp) getContext().getApplicationContext()).getAppComponent().inject(this);

        View view = inflater.inflate(R.layout.top_rated_movies_fragment, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);



        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        final Observable<MovieResponse> movieObservable = apiManager.getPopularMovies(API_KEY);
        compositeSubscription.add(movieObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieResponse>() {
                    @Override
                    public void call(MovieResponse movieResponse) {
                        ArrayList<Movie> movies = movieResponse.getResults();
                        recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.movie_list_item, getContext(), picasso));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                }));
        return view;
    }

    @Override
    public void onDestroy() {
        compositeSubscription.unsubscribe();
        super.onDestroy();
    }
}