package com.myf.health.ui.content;

import com.myf.health.ui.BasePresenter;
import com.myf.health.ui.BaseView;

import java.util.List;

/**
 * Created by MaoYouFeng on 2016/5/26.
 */
public interface ContentContract{
    public interface View extends BaseView{
        void showLoading();
        void hideLoading();
        //void onError(String error);

    }

    public interface Presenter extends BasePresenter<View>{
        void onCookInfoReceive(Long id);
        void onHealthInfoReceive(Long id);
        void onReload();
    }
}
