// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.android.java.miss.tmdbmovies;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class Fetcher_Factory implements Factory<Fetcher> {
  private final Provider<Param> paramProvider;

  public Fetcher_Factory(Provider<Param> paramProvider) {
    assert paramProvider != null;
    this.paramProvider = paramProvider;
  }

  @Override
  public Fetcher get() {
    return new Fetcher(paramProvider.get());
  }

  public static Factory<Fetcher> create(Provider<Param> paramProvider) {
    return new Fetcher_Factory(paramProvider);
  }
}
