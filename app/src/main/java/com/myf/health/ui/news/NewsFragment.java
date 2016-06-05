package com.myf.health.ui.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.myf.health.R;
import com.myf.health.bean.NewsBean;
import com.myf.health.ui.BaseFragment;
import com.myf.health.widget.LoadMoreRecyclerView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by MaoYouFeng on 2016/6/3.
 */
public class NewsFragment extends BaseFragment implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.LoadMoreListener {
    private int type;
    public static NewsFragment newInstance(int type,String title) {
        NewsFragment mFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("type",type);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Inject
    NewsPresenter mPresenter;
    @Inject
    Activity mActivity;

    @Inject
    NewsAdapter mAdapter;

    @Bind(R.id.recyclerView)
    LoadMoreRecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void getBundle(Bundle bundle) {
        type=bundle.getInt("type");
    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        refreshLayout.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadMoreListener(this);
    }

    @Override
    public void initData() {
        mPresenter.onDataReceive(type);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_list;
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh(type);
    }

    @Override
    public void onLoadMore() {
        mPresenter.onLoadMore(type);
    }

    @Override
    public void showLoading() {
        showProgress(true);
    }

    @Override
    public void hideLoading() {
        showContent(true);
    }

    @Override
    public void onError(String error) {
        setErrorText(error);
        showError(true);
    }

    @Override
    public void rendarDataList(List<NewsBean> newsList) {
        mAdapter.setmDate(newsList);
    }

    @Override
    public void onLoadCompleted(boolean hasMore) {
        recyclerView.onLoadCompleted(hasMore);
    }

    @Override
    public void onRefreshCompleted() {
        refreshLayout.setRefreshing(false);
    }
}
