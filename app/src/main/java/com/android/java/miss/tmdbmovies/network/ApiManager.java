package com.android.java.miss.tmdbmovies.network;

import com.android.java.miss.tmdbmovies.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by suadahaji.
 */

public interface ApiManager {

  @GET("movie/top_rated")
  Observable<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
