package com.android.java.miss.tmdbmovies;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.android.java.miss.tmdbmovies.adapters.ViewPagerAdapter;
import com.android.java.miss.tmdbmovies.fragments.NowPlayingMoviesFragment;
import com.android.java.miss.tmdbmovies.fragments.PopularMoviesFragment;
import com.android.java.miss.tmdbmovies.fragments.TopRatedMoviesFragment;
import com.android.java.miss.tmdbmovies.fragments.UpcomingMoviesFragment;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.viewpager) ViewPager viewPager;

    @Bind(R.id.slidingTabs) TabLayout tabLayout;

    @Inject @ApiKey String apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movies");

        ((MoviesApp) getApplicationContext()).getAppComponent().inject(this);
        if (apiKey.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG);
            return;
        }

        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TopRatedMoviesFragment(), "TOP RATED");
        adapter.addFragment(new UpcomingMoviesFragment(), "UPCOMING");
        adapter.addFragment(new NowPlayingMoviesFragment(), "NOW PLAYING");
        adapter.addFragment(new PopularMoviesFragment(), "POPULAR");
        viewPager.setAdapter(adapter);
    }
}
