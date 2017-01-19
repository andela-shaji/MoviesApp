package com.android.java.miss.tmdbmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.java.miss.tmdbmovies.R;
import com.android.java.miss.tmdbmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by suadahaji.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private ArrayList<Movie> movies;
  private int rowLayout;
  private static Context context;
  private Picasso picasso;
  private static final String TAG = MoviesAdapter.class.getSimpleName();

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView moviePoster;
    TextView movieTitle;
    TextView date;
    TextView rating;
    String movie_id;

    public MovieViewHolder(View v) {
      super(v);

      moviePoster = (ImageView) v.findViewById(R.id.iv_image);
      movieTitle = (TextView) v.findViewById(R.id.movie_title);
      date = (TextView) v.findViewById(R.id.movie_date);
      rating = (TextView) v.findViewById(R.id.rating);
    }
  }

  public MoviesAdapter(ArrayList<Movie> movies, int rowLayout, Context context, Picasso picasso) {
    this.movies = movies;
    this.rowLayout = rowLayout;
    this.context = context;
    this.picasso = picasso;
  }

  @Override
  public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MovieViewHolder holder, final int position) {
    holder.movie_id = String.valueOf(movies.get(position).getId());

      holder.movieTitle.setText(movies.get(position).getTitle());
      holder.date.setText(movies.get(position).getReleaseDate());
      holder.rating.setText(Double.toString(movies.get(position).getVoteAverage()));
      picasso.load("http://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath()).into(holder.moviePoster);
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }
}
