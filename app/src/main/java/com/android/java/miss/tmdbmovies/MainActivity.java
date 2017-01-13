package com.android.java.miss.tmdbmovies;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.java.miss.tmdbmovies.fragments.TopRatedMoviesFragment;
import javax.inject.Inject;

import static com.android.java.miss.tmdbmovies.utils.Constants.API_KEY;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

  @Inject @ApiKey String apiKey;

  @Inject DataExtractor dataExtractor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((MoviesApp) getApplicationContext()).getAppComponent().plus(new MainModule()).inject(this);

    if (apiKey.isEmpty()) {
      Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG);
      return;
    }

    dataExtractor.extractData();

    TopRatedMoviesFragment topRatedMoviesFragment = new TopRatedMoviesFragment();
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.fragment_frame, topRatedMoviesFragment).commit();
  }


}
