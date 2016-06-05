package com.myf.health.injector.component;

import android.app.Activity;

import com.myf.health.injector.PerActivity;
import com.myf.health.injector.module.ActivityModule;
import com.myf.health.ui.BaseActivity;
import com.myf.health.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivityContext();

    void inject(BaseActivity mBaseActivity);
    //void inject(MainActivity mMainActivity);
    void inject(MainActivity mMainActivity);
}
