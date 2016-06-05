package com.myf.health.injector.module;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;

import com.myf.health.components.okhttp.HttpLoggingInterceptor;
import com.squareup.otto.Bus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
@Module
public class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context=context;
    }
    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    public Bus provideBusEvent(){
        return new Bus();
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                        .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                /*.addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        Log.d("log",response.body().string());
                        return response;
                    }
                })*/;
        return builder.build();
    }
}
