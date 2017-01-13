package com.android.java.miss.tmdbmovies;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.java.miss.tmdbmovies.fragments.TopRatedMoviesFragment;

import static com.android.java.miss.tmdbmovies.utils.Constants.API_KEY;

public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    if (API_KEY.isEmpty()) {
      Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG);
      return;
    }

    TopRatedMoviesFragment topRatedMoviesFragment = new TopRatedMoviesFragment();
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.fragment_frame, topRatedMoviesFragment).commit();



  }

}
