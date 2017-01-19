package com.android.java.miss.tmdbmovies.movies.upcoming;

import com.android.java.miss.tmdbmovies.model.Movie;
import java.util.ArrayList;

public interface UpcomingScreen {
    void onMoviesResponse(ArrayList<Movie> movies);
}
