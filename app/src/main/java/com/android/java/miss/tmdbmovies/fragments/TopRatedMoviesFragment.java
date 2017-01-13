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

import com.android.java.miss.tmdbmovies.R;
import com.android.java.miss.tmdbmovies.adapters.MoviesAdapter;
import com.android.java.miss.tmdbmovies.model.Movie;
import com.android.java.miss.tmdbmovies.model.MovieResponse;
import com.android.java.miss.tmdbmovies.network.ApiClient;
import com.android.java.miss.tmdbmovies.network.ApiManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.java.miss.tmdbmovies.utils.Constants.API_KEY;
import static com.makeramen.roundedimageview.RoundedDrawable.TAG;


public class TopRatedMoviesFragment extends Fragment {

  public TopRatedMoviesFragment() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.top_rated_movies_fragment, container, false);
    final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.movies_recycler_view);
    recyclerView.setHasFixedSize(true);


      recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));



    ApiManager apiService = ApiClient.getClient().create(ApiManager.class);

    Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
    call.enqueue(new Callback<MovieResponse>() {
      @Override
      public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        ArrayList<Movie> movies = response.body().getResults();
        recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.movie_list_item, getContext()));
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t) {
        Log.e(TAG, t.toString());
      }
    });
    return view;
  }
}