package com.myf.health.injector.component;

import android.app.Activity;

import com.myf.health.injector.PerFragment;
import com.myf.health.injector.module.FragmentModule;
import com.myf.health.ui.BaseFragment;
import com.myf.health.ui.news.NewsFragment;

import dagger.Component;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
@PerFragment
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(BaseFragment mBaseFragment);
    void inject(NewsFragment mNewsFragment);
}
