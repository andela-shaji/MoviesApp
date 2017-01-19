package com.android.java.miss.tmdbmovies.network;

import com.android.java.miss.tmdbmovies.model.MovieResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by suadahaji.
 */

public interface ApiManager {

  @GET("movie/top_rated")
  Observable<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

  @GET("movie/popular")
  Observable<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

  @GET("movie/upcoming")
  Observable<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);

  @GET("movie/now_playing")
  Observable<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);
}
