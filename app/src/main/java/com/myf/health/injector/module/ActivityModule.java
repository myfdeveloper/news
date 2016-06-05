package com.myf.health.injector.module;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

import com.myf.health.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
@Module
public class ActivityModule {
    private Activity mActivity;


    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

}
