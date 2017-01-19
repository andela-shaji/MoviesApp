package com.android.java.miss.tmdbmovies;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class FetcherTest {

    Fetcher fetcher;

    @Mock Param param;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        fetcher = new Fetcher(param);
    }

    @Test
    public void fetchCallsRetrieveParam() throws Exception {
        //Give

        //When
        fetcher.fetch();

        //Then
        verify(param, times(1)).retrieve();
    }
}
