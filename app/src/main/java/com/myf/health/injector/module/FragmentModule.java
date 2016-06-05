package com.myf.health.injector.module;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.myf.health.injector.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return mFragment.getActivity();
    }


}
