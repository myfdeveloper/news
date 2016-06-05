package com.myf.health.ui.news;

import com.myf.health.bean.NewsBean;
import com.myf.health.ui.BasePresenter;
import com.myf.health.ui.BaseView;

import java.util.List;

/**
 * Created by MaoYouFeng on 2016/6/3.
 */
public interface NewsContract {
    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void onError(String error);

        void rendarDataList(List<NewsBean> newsList);

        void onLoadCompleted(boolean hasMore);

        void onRefreshCompleted();
    }

    interface Presenter extends BasePresenter<View> {
        void onDataReceive(int type);
        void onRefresh(int type);
        void onLoadMore(int type);
    }
}
