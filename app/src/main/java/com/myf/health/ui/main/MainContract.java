package com.myf.health.ui.main;

import com.myf.health.ui.BasePresenter;
import com.myf.health.ui.BaseView;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
public interface MainContract {
    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{
        void exist();
    }
}
