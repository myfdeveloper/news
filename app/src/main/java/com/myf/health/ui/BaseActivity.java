package com.myf.health.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myf.health.AppManager;
import com.myf.health.MyApplication;
import com.myf.health.injector.component.ActivityComponent;
import com.myf.health.injector.component.DaggerActivityComponent;
import com.myf.health.injector.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .build();
        mActivityComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        initInjector();
        initUiAndListener();
        AppManager.getAppManager().addActivity(this);
    }

    public abstract int initContentView();

    public abstract void initInjector();

    public abstract void initUiAndListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
