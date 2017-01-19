// Generated code from Butter Knife. Do not modify!
package com.android.java.miss.tmdbmovies;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.android.java.miss.tmdbmovies.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558519, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558519, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558521, "field 'viewPager'");
    target.viewPager = finder.castView(view, 2131558521, "field 'viewPager'");
    view = finder.findRequiredView(source, 2131558520, "field 'tabLayout'");
    target.tabLayout = finder.castView(view, 2131558520, "field 'tabLayout'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
    target.viewPager = null;
    target.tabLayout = null;
  }
}
