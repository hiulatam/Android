package com.hiulatam.hiu.hiu.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hiulatam.hiu.hiu.R;

import java.util.List;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/5/17
 */

public class CelebrityFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "CelebrityFragmentPagerAdapter - ";

    private List<Fragment> fragments;
    private String[] pageTitles;


    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @param fm
     */
    public CelebrityFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);

        this.fragments = fragments;

        pageTitles = context.getResources().getStringArray(R.array.page_title);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @return
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return pageTitles[position];
    }
}
