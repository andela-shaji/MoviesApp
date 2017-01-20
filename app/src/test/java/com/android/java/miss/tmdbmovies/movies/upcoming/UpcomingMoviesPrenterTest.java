package com.android.java.miss.tmdbmovies.movies.upcoming;

import com.android.java.miss.tmdbmovies.model.Movie;
import com.android.java.miss.tmdbmovies.model.MovieResponse;
import com.android.java.miss.tmdbmovies.network.ApiManager;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import rx.Observable;
import rx.plugins.RxJavaSchedulersTestRule;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpcomingMoviesPrenterTest {

    UpcomingMoviesPresenter presenter;

    @Mock ApiManager apiManager;
    @Mock UpcomingScreen screen;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule public RxJavaSchedulersTestRule rxJavaSchedulersTestRule = new RxJavaSchedulersTestRule();

    @Before
    public void setUp() throws Exception {
        presenter = new UpcomingMoviesPresenter(apiManager, screen);
    }

    @Test
    public void successFetchMoviesCallsScreenOnResponse() throws Exception {
        //Given
        MovieResponse mockMoviesResponse = mock(MovieResponse.class);
        ArrayList<Movie> mockMovies = new ArrayList<>();
        mockMovies.add(mock(Movie.class));
        mockMovies.add(mock(Movie.class));
        when(mockMoviesResponse.getResults()).thenReturn(mockMovies);

        when(apiManager.getUpcomingMovies(anyString())).thenReturn(
            Observable.from(new MovieResponse[] { mockMoviesResponse }));

        //When
        presenter.fetchMovies();

        //Then
        verify(screen, times(1)).onMoviesResponse(mockMovies);
    }

    @Test
    public void errorFetchsMoviesCallsScreenOnError() throws Exception {
        //Given
        when(apiManager.getUpcomingMovies(anyString())).thenReturn(
            Observable.<MovieResponse>error(new RuntimeException("I'm a Huge error")));

        //When
        presenter.fetchMovies();

        //then
        verify(screen, times(1)).displayErrorState();
    }

    @Test
    public void emptyResultCallsEmptyStateScreen() throws Exception {
        //Given
        MovieResponse mockMoviesResponse = mock(MovieResponse.class);
        when(mockMoviesResponse.getResults()).thenReturn(new ArrayList<Movie>());
        when(apiManager.getUpcomingMovies(anyString())).thenReturn(
            Observable.just(mockMoviesResponse));

        //When
        presenter.fetchMovies();

        //Then
        verify(screen, times(1)).displayEmptyState();
    }
}

