package com.myf.health.ui.main;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.myf.health.AppManager;
import com.myf.health.helper.ToastHelper;
import com.squareup.otto.Bus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
public class MainPresenter implements MainContract.Presenter{

    @Inject
    Bus mBus;
    @Inject
    ToastHelper mToastHelper;
    @Inject
    Activity mActivity;

    private MainContract.View mMainView;

    @Inject
    @Singleton
    public MainPresenter() {
    }

    @Override
    public void attachView(@NonNull MainContract.View view) {
        mMainView = view;
        mBus.register(this);
    }

    @Override
    public void detachView() {

    }

    private long mExitTime = 0;

    private boolean isCanExit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            mToastHelper.showToast("再按一次退出程序");
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }

    @Override
    public void exist() {
        if (isCanExit()) {
            AppManager.getAppManager().AppExit(mActivity);
        }
    }
}
