package com.myf.health.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myf.health.R;
import com.myf.health.ui.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaoYouFeng on 2016/5/23.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mListFragment = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mListFragment.add(NewsFragment.newInstance(0,"头条"));
        mListFragment.add(NewsFragment.newInstance(1,"NBA"));
        mListFragment.add(NewsFragment.newInstance(2,"汽车"));
        mListFragment.add(NewsFragment.newInstance(3,"笑话"));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListFragment.get(position).getArguments().getString("title");
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }
}
