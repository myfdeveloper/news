package com.myf.health.ui.news;

import android.support.annotation.NonNull;

import com.myf.health.api.forum.NewsApi;
import com.myf.health.bean.NewsBean;
import com.myf.health.constants.Urls;
import com.myf.health.helper.ToastHelper;
import com.squareup.otto.Produce;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by MaoYouFeng on 2016/6/3.
 */
public class NewsPresenter implements NewsContract.Presenter {
    @Inject
    NewsApi mNewsApi;
    @Inject
    ToastHelper mToastHelper;

    private NewsContract.View mNewsView;
    private Subscription mSubscription;

    private List<NewsBean> newsBeen=new ArrayList<NewsBean>();
    @Inject
    public NewsPresenter() {
    }

    private int pageIndex = 0;
    @Override
    public void attachView(@NonNull NewsContract.View view) {
        mNewsView=view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void onDataReceive(int type) {
        mNewsView.showLoading();
        loadNews(type,pageIndex);

    }

    @Override
    public void onRefresh(int type) {
        pageIndex=0;
        newsBeen.clear();
        loadNews(type,pageIndex);
    }

    @Override
    public void onLoadMore(int type) {
        System.out.println("onLoadMore-->"+pageIndex);
        pageIndex += Urls.PAZE_SIZE;
        loadNews(type,pageIndex);

    }

    private void loadNews(final int type, final int pageIndex) {
        mSubscription=mNewsApi.getNews(type,pageIndex)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<NewsBean>>() {
                    @Override
                    public void call(List<NewsBean> newsBeen) {
                        addList(newsBeen);
                        mNewsView.hideLoading();
                        mNewsView.onRefreshCompleted();
                        mNewsView.onLoadCompleted(true);
                    }
                });
    }

    private void addList(List<NewsBean> newsBeen) {
        this.newsBeen.addAll(newsBeen);
        mNewsView.rendarDataList(this.newsBeen);
    }
}
