package com.myf.health.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myf.health.MyApplication;
import com.myf.health.R;
import com.myf.health.injector.component.DaggerFragmentComponent;
import com.myf.health.injector.component.FragmentComponent;
import com.myf.health.injector.module.FragmentModule;
import com.myf.health.widget.ProgressBarCircularIndeterminate;
import com.myf.health.widget.ProgressFragment;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
public abstract class BaseFragment extends ProgressFragment {
    protected FragmentComponent mFragmentComponent;
    private TextView tvError, tvEmpty, tvLoading;
    private Button btnReload;

    public abstract void initInjector();
    public abstract void getBundle(Bundle bundle);
    public abstract void initUI(View view);
    public abstract void initData();
    public abstract int initContentView();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(((MyApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
        initInjector();
        getBundle(getArguments());
        initUI(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater) {
        return inflater.inflate(initContentView(), null);
    }

    @Override
    public View onCreateContentErrorView(LayoutInflater inflater) {
        View error = inflater.inflate(R.layout.error_view_layout, null);
        tvError = (TextView) error.findViewById(R.id.tvError);
        error.findViewById(R.id.btnReload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReloadClicked();
            }
        });
        return error;
    }

    @Override
    public View onCreateContentEmptyView(LayoutInflater inflater) {
        View empty = inflater.inflate(R.layout.empty_view_layout, null);
        tvEmpty = (TextView) empty.findViewById(R.id.tvEmpty);
        btnReload = (Button) empty.findViewById(R.id.btnReload);
        empty.findViewById(R.id.btnReload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReloadClicked();
            }
        });
        return empty;
    }

    @Override
    public View onCreateProgressView(LayoutInflater inflater) {
        View loading = inflater.inflate(R.layout.loading_view_layout, null);
        tvLoading = (TextView) loading.findViewById(R.id.tvLoading);
        ProgressBarCircularIndeterminate progressBar = (ProgressBarCircularIndeterminate) loading.findViewById(R.id.progress_view);
        progressBar.setBackgroundColor(getThemeColor(getActivity()));
        return loading;
    }

    public int getThemeColor(Context mContext) {
        int materialBlue = mContext.getResources().getColor(R.color.md_green_500);
        return resolveColor(mContext, R.attr.colorPrimary, materialBlue);
    }

    private int resolveColor(Context mContext, @AttrRes int attr, int fallback) {
        TypedArray a = mContext.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getColor(0, fallback);
        } finally {
            a.recycle();
        }
    }

    public void setErrorText(String text) {
        tvError.setText(text);
    }

    public void setErrorText(int textResId) {
        setErrorText(getString(textResId));
    }

    public void setEmptyText(String text) {
        tvEmpty.setText(text);
    }

    public void setEmptyButtonVisible(int visible) {
        btnReload.setVisibility(visible);
    }

    public void setEmptyText(int textResId) {
        setEmptyText(getString(textResId));
    }

    public void setLoadingText(String text) {
        tvLoading.setText(text);
    }

    public void setLoadingText(int textResId) {
        setLoadingText(getString(textResId));
    }

    //Override this to reload
    public void onReloadClicked() {

    }
}
