package com.android.java.miss.tmdbmovies.movies.upcoming;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.android.java.miss.tmdbmovies.MoviesApp;
import com.android.java.miss.tmdbmovies.R;
import com.android.java.miss.tmdbmovies.adapters.MoviesAdapter;
import com.android.java.miss.tmdbmovies.model.Movie;
import com.android.java.miss.tmdbmovies.network.ApiManager;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;

public class UpcomingMoviesView extends RelativeLayout implements UpcomingScreen {

    @Inject ApiManager apiManager;

    @Inject Picasso picasso;

    CompositeSubscription compositeSubscription = new CompositeSubscription();
    private final RecyclerView recyclerView;

    UpcomingMoviesPresenter presenter;

    public UpcomingMoviesView(Context context) {
        super(context);
        final View view =
            LayoutInflater.from(context).inflate(R.layout.top_rated_movies, this, true);

        ((MoviesApp) getContext().getApplicationContext()).getAppComponent().inject(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        presenter = new UpcomingMoviesPresenter(apiManager, picasso, this);
        presenter.fetch();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        compositeSubscription.unsubscribe();
    }

    @Override
    public void onMoviesResponse(ArrayList<Movie> movies) {
        recyclerView.setAdapter(
            new MoviesAdapter(movies, R.layout.movie_list_item, getContext(), picasso));
    }
}
