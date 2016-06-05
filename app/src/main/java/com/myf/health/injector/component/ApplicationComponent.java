package com.myf.health.injector.component;

import android.content.Context;

import com.myf.health.MyApplication;
import com.myf.health.api.forum.NewsApi;
import com.myf.health.helper.ToastHelper;
import com.myf.health.injector.module.ApiModule;
import com.myf.health.injector.module.ApplicationModule;
import com.myf.health.injector.module.HelperModule;
import com.myf.health.widget.MyWebView;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
@Singleton
@Component(modules = {ApplicationModule.class, HelperModule.class, ApiModule.class})
public interface ApplicationComponent {

    Context getContext();
    Bus getBus();

    NewsApi getNewsApi();
    ToastHelper getToastHelper();
    OkHttpClient getOkHttpClient();

    void inject(MyApplication mApplication);

    void inject(MyWebView myWebView);
}
