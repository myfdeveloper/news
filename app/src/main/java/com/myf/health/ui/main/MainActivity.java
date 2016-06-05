package com.myf.health.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.myf.health.R;
import com.myf.health.ui.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View{

    public static void startActivity(Context mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pager)
    ViewPager pager;

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;

    @Inject
    MainPresenter mPresenter;


    private ViewPagerAdapter mAdapter;
    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("健康资讯");
        mAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mAdapter);
        tabs.setViewPager(pager);
        mPresenter.attachView(this);
    }

    @Override
    public void onBackPressed() {
        mPresenter.exist();
    }

}
