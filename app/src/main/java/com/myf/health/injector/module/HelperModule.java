package com.myf.health.injector.module;

import android.content.Context;

import com.myf.health.helper.ToastHelper;

import java.util.Calendar;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MaoYouFeng on 2016/5/24.
 */

@Module
public class HelperModule {
    @Provides
    @Singleton
    ToastHelper provideToastHelper(Context mContext) {
        return new ToastHelper(mContext);
    }

}
