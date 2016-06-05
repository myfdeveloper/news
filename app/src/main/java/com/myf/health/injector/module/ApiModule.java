package com.myf.health.injector.module;

import com.myf.health.api.forum.NewsApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by MaoYouFeng on 2016/5/24.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public NewsApi provideNewsApi(OkHttpClient mOkHttpClient){
        return new NewsApi(mOkHttpClient);
    }

}
